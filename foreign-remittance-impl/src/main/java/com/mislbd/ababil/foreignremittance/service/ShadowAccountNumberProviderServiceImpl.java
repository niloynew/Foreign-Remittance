package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.asset.exception.ConfigurationNotFoundException;
import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.exception.GeneratedShadowAccountNumberNotFoundException;
import com.mislbd.ababil.foreignremittance.exception.IDProductNotFoundException;
import com.mislbd.ababil.foreignremittance.repository.jpa.GeneratedShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.IDProductRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountNumberSequenceRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.GeneratedShadowAccountEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.IDProductEntity;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowAccountNumberSequenceEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ShadowAccountNumberProviderServiceImpl implements ShadowAccountNumberProviderService {

  private static final String ACCOUNT_FORMAT = "%%0%dd%%%ds%%0%dd";
  public static final String BRANCH_CODE_LENGTH_KEY = "BRANCH_CODE_DIGIT";
  public static final String PRODUCT_CODE_LENGTH_KEY = "PRODUCT_CODE_DIGIT";
  public static final String ACCOUNT_LENGTH_KEY = "TOTAL_ACCOUNT_NO_DIGIT";
  public static final String MAX_ACCOUNT_RESERVATION_TIME_KEY = "MAX_ACCOUNT_RESERVATION_TIME";
  public static final String ACCOUNT_CHECK_DIGIT_LENGTH_KEY = "ACCOUNT_CHECK_DIGIT_LENGTH";
  public static final boolean UNRESERVED = false;

  protected final ConfigurationService configurationService;
  protected final GeneratedShadowAccountRepository generatedShadowAccountRepository;
  protected final IDProductRepository idProductRepository;
  protected final ShadowAccountNumberSequenceRepository shadowAccountNumberSequenceRepository;

  public ShadowAccountNumberProviderServiceImpl(
      ConfigurationService configurationService,
      GeneratedShadowAccountRepository generatedShadowAccountRepository,
      IDProductRepository idProductRepository,
      ShadowAccountNumberSequenceRepository shadowAccountNumberSequenceRepository) {
    this.configurationService = configurationService;
    this.generatedShadowAccountRepository = generatedShadowAccountRepository;
    this.idProductRepository = idProductRepository;
    this.shadowAccountNumberSequenceRepository = shadowAccountNumberSequenceRepository;
  }

  @Override
  public void makeAccountUsed(String accountNumber) {
    Optional<GeneratedShadowAccountEntity> account =
        generatedShadowAccountRepository.findOneByAccountNumber(accountNumber);
    if (account.isPresent()) {
      generatedShadowAccountRepository.deleteById(account.get().getId());
    } else {
      throw new GeneratedShadowAccountNumberNotFoundException(
          String.format("Generated shadow account '%s' not found", accountNumber));
    }
  }

  @Override
  public int calculateCheckDigit(String accountNumber) {

    int sum = 0;
    int length = accountNumber.length();

    for (int i = 0; i < length; i++) {
      int digit = Integer.parseInt(accountNumber.substring(i, i + 1));
      if (i % 2 == 1) {
        digit *= 2;
      }
      sum += digit > 9 ? digit - 9 : digit;
    }
    int modulusOfSum = sum % 10;
    int checkDigit = modulusOfSum == 0 ? 9 : 10 - modulusOfSum;
    return checkDigit;
  }

  @Override
  @Transactional(readOnly = false)
  public String getAccountNumber(long productId, long branchId, String username) {
    Integer branchCodeLength =
        configurationService
            .getConfiguration(BRANCH_CODE_LENGTH_KEY)
            .map(conf -> Integer.valueOf(conf.getValue()))
            .orElseThrow(
                () ->
                    new ConfigurationNotFoundException(
                        "Configuration not found: " + BRANCH_CODE_LENGTH_KEY));

    Integer productCodeLength =
        configurationService
            .getConfiguration(PRODUCT_CODE_LENGTH_KEY)
            .map(conf -> Integer.valueOf(conf.getValue()))
            .orElseThrow(
                () ->
                    new ConfigurationNotFoundException(
                        "Configuration not found: " + PRODUCT_CODE_LENGTH_KEY));

    Integer accountLength =
        configurationService
            .getConfiguration(ACCOUNT_LENGTH_KEY)
            .map(conf -> Integer.valueOf(conf.getValue()))
            .orElseThrow(
                () ->
                    new ConfigurationNotFoundException(
                        "Configuration not found: " + ACCOUNT_LENGTH_KEY));

    Integer checkDigitLength =
        configurationService
            .getConfiguration(ACCOUNT_CHECK_DIGIT_LENGTH_KEY)
            .map(conf -> Integer.valueOf(conf.getValue()))
            .orElseThrow(
                () ->
                    new ConfigurationNotFoundException(
                        "Configuration not found: " + ACCOUNT_CHECK_DIGIT_LENGTH_KEY));

    Integer maxReservationTime =
        configurationService
            .getConfiguration(MAX_ACCOUNT_RESERVATION_TIME_KEY)
            .map(conf -> Integer.valueOf(conf.getValue()))
            .orElseThrow(
                () ->
                    new ConfigurationNotFoundException(
                        "Configuration not found: " + MAX_ACCOUNT_RESERVATION_TIME_KEY));

    return getUnreservedAccount(productId, branchId)
        .map(account -> reserveAndGetAccountNumber(account, maxReservationTime, username))
        .orElseGet(
            () ->
                generateReserveAndGetAccountNumber(
                    productId,
                    branchId,
                    branchCodeLength,
                    productCodeLength,
                    checkDigitLength,
                    accountLength,
                    maxReservationTime,
                    username));
  }

  private Optional<GeneratedShadowAccountEntity> getUnreservedAccount(
      long productId, long branchId) {
    freeExpiredReservations();
    return generatedShadowAccountRepository.findFirstByReservedAndBranchIdAndProductId(
        UNRESERVED, branchId, productId);
  }

  public void freeExpiredReservations() {
    List<GeneratedShadowAccountEntity> accounts =
        generatedShadowAccountRepository.findAllByReservationEndsOnLessThan(LocalDateTime.now());
    accounts.forEach(
        account -> {
          account.setReserved(false);
          account.setReservationEndsOn(null);
          account.setReservedBy(null);
        });
  }

  private String reserveAndGetAccountNumber(
      GeneratedShadowAccountEntity account, Integer maxReservationTime, String username) {
    account.setReserved(true);
    account.setReservationEndsOn(calculateReservationEndTime(maxReservationTime));
    account.setReservedBy(username);
    return account.getAccountNumber();
  }

  private LocalDateTime calculateReservationEndTime(Integer maxReservationTime) {
    return LocalDateTime.now().plusMinutes(maxReservationTime);
  }

  private String generateReserveAndGetAccountNumber(
      long productId,
      long branchId,
      Integer branchCodeLength,
      int productCodeLength,
      int checkDigitLength,
      int accountLength,
      int maxReservationTime,
      String username) {

    IDProductEntity product =
        idProductRepository.findById(productId).orElseThrow(IDProductNotFoundException::new);

    ShadowAccountNumberSequenceEntity sequence =
        shadowAccountNumberSequenceRepository
            .findOneByBranchIdAndProductId(branchId, productId)
            .orElseGet(() -> createAndGetAccountNumberSequence(productId, branchId));

    int accountSequenceLength =
        accountLength - (branchCodeLength + productCodeLength + checkDigitLength);

    String accountNumber =
        String.format(
                String.format(
                    ACCOUNT_FORMAT, branchCodeLength, productCodeLength, accountSequenceLength),
                branchId,
                product.getCode(),
                sequence.getSequence())
            .replace(' ', '0');

    if (checkDigitLength > 0) {
      accountNumber += calculateCheckDigit(accountNumber);
    }

    sequence.setSequence(sequence.getSequence() + 1);

    GeneratedShadowAccountEntity generatedAccount = new GeneratedShadowAccountEntity();
    generatedAccount.setId(generatedShadowAccountRepository.maxId() + 1);
    generatedAccount.setBranchId(branchId);
    generatedAccount.setProductId(productId);
    generatedAccount.setReserved(true);
    generatedAccount.setReservedBy(username);
    generatedAccount.setReservationEndsOn(calculateReservationEndTime(maxReservationTime));
    generatedAccount.setAccountNumber(accountNumber);
    generatedShadowAccountRepository.save(generatedAccount);

    return accountNumber;
  }

  private ShadowAccountNumberSequenceEntity createAndGetAccountNumberSequence(
      long productId, long branchId) {
    ShadowAccountNumberSequenceEntity newSequence = new ShadowAccountNumberSequenceEntity();
    newSequence.setId(shadowAccountNumberSequenceRepository.maxId() + 1);
    newSequence.setBranchId(branchId);
    newSequence.setProductId(productId);
    newSequence.setSequence(1L);
    shadowAccountNumberSequenceRepository.save(newSequence);
    return shadowAccountNumberSequenceRepository
        .findOneByBranchIdAndProductId(branchId, productId)
        .get();
  }
}
