// package com.mislbd.ababil.foreignremittance.service;
// import com.mislbd.ababil.asset.exception.ConfigurationNotFoundException;
// import com.mislbd.ababil.asset.service.ConfigurationService;
// import com.mislbd.ababil.foreignremittance.repository.jpa.AccountNumberSequenceRepository;
// import com.mislbd.ababil.foreignremittance.repository.jpa.GeneratedAccountRepository;
// import com.mislbd.ababil.foreignremittance.repository.jpa.IDProductRepository;
// import com.mislbd.ababil.foreignremittance.repository.schema.AccountNumberSequenceEntity;
// import com.mislbd.ababil.foreignremittance.repository.schema.IDProductEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
// @Service
// @Transactional(readOnly = true)
// public class DefaultServiceImpl implements DefaultService {
//
//
//    private static final String ACCOUNT_FORMAT = "%%0%dd%%%ds%%0%dd";
//    public static final String BRANCH_CODE_LENGTH_KEY = "BRANCH_CODE_DIGIT";
//    public static final String PRODUCT_CODE_LENGTH_KEY = "PRODUCT_CODE_DIGIT";
//    public static final String ACCOUNT_LENGTH_KEY = "TOTAL_ACCOUNT_NO_DIGIT";
//    public static final String MAX_ACCOUNT_RESERVATION_TIME_KEY = "MAX_ACCOUNT_RESERVATION_TIME";
//    public static final String ACCOUNT_CHECK_DIGIT_LENGTH_KEY = "ACCOUNT_CHECK_DIGIT_LENGTH";
//    public static final boolean UNRESERVED = false;
//
//    protected final ConfigurationService configurationService;
//    protected final IDProductRepository idProductRepository;
//
//    protected final GeneratedAccountRepository generatedAccountRepository;
//    protected final AccountNumberSequenceRepository accountNumberSequenceRepository;
//
//
//    public DefaultServiceImpl(ConfigurationService configurationService, IDProductRepository
// idProductRepository, GeneratedAccountRepository generatedAccountRepository,
// AccountNumberSequenceRepository accountNumberSequenceRepository) {
//        this.configurationService = configurationService;
//        this.idProductRepository = idProductRepository;
//        this.generatedAccountRepository = generatedAccountRepository;
//        this.accountNumberSequenceRepository = accountNumberSequenceRepository;
//    }
//
//
//    @Transactional(readOnly = false)
//    @Override
//    public String getAccountNumber(long productId, long branchId, String username) {
//
//        Integer branchCodeLength =
//                configurationService
//                        .getConfiguration(BRANCH_CODE_LENGTH_KEY)
//                        .map(conf -> Integer.valueOf(conf.getValue()))
//                        .orElseThrow(
//                           () ->
//                               new ConfigurationNotFoundException(
//                                   "Configuration not found: " + BRANCH_CODE_LENGTH_KEY));
//
//
//        Integer productCodeLength =
//                configurationService
//                        .getConfiguration(PRODUCT_CODE_LENGTH_KEY)
//                        .map(conf -> Integer.valueOf(conf.getValue()))
//                        .orElseThrow(
//                           () ->
//                               new ConfigurationNotFoundException(
//                                    "Configuration not found: " + PRODUCT_CODE_LENGTH_KEY));
//
//
//        Integer accountLength =
//                configurationService
//                        .getConfiguration(ACCOUNT_LENGTH_KEY)
//                        .map(conf -> Integer.valueOf(conf.getValue()))
//                        .orElseThrow(
//                                () ->
//                                        new ConfigurationNotFoundException(
//                                                "Configuration not found: " +
// ACCOUNT_LENGTH_KEY));
//
//        Integer checkDigitLength =
//                configurationService
//                        .getConfiguration(ACCOUNT_CHECK_DIGIT_LENGTH_KEY)
//                        .map(conf -> Integer.valueOf(conf.getValue()))
//                        .orElseThrow(
//                                () ->
//                                        new ConfigurationNotFoundException(
//                                                "Configuration not found: " +
// ACCOUNT_CHECK_DIGIT_LENGTH_KEY));
//
//        Integer maxReservationTime =
//                configurationService
//                        .getConfiguration(MAX_ACCOUNT_RESERVATION_TIME_KEY)
//                        .map(conf -> Integer.valueOf(conf.getValue()))
//                        .orElseThrow(
//                                () ->
//                                     new ConfigurationNotFoundException(
//                                         "Configuration not found: " +
// MAX_ACCOUNT_RESERVATION_TIME_KEY));
//
//
//        return generateReserveAndGetAccountNumber(
//                                        productId,
//                                        branchId,
//                                        branchCodeLength,
//                                        productCodeLength,
//                                        checkDigitLength,
//                                        accountLength,
//                                        maxReservationTime,
//                                        username);
//
//    }
//
//
//    private String generateReserveAndGetAccountNumber(
//            long productId,
//            long branchId,
//            Integer branchCodeLength,
//            int productCodeLength,
//            int checkDigitLength,
//            int accountLength,
//            int maxReservationTime,
//            String username) {
//
//        IDProductEntity product =
//
// idProductRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
//
//        AccountNumberSequenceEntity sequence =
//                accountNumberSequenceRepository
//                        .findOneByBranchIdAndProductId(branchId, productId)
//                        .orElseGet(() -> createAndGetAccountNumberSequence(productId, branchId));
//
//        int accountSequenceLength =
//                accountLength - (branchCodeLength + productCodeLength + checkDigitLength);
//
//        String accountNumber =
//                String.format(
//                        String.format(
//                                ACCOUNT_FORMAT, branchCodeLength, productCodeLength,
// accountSequenceLength),
//                        branchId,
//                        product.getCode(),
//                        sequence.getSequence())
//                        .replace(' ', '0');
//
//        if (checkDigitLength > 0) {
//            accountNumber += calculateCheckDigit(accountNumber);
//        }
//
//        sequence.setSequence(sequence.getSequence() + 1);
//
//        GeneratedAccountEntity generatedAccount = new GeneratedAccountEntity();
//        generatedAccount.setId(generatedAccountRepository.maxId() + 1);
//        generatedAccount.setBranchId(branchId);
//        generatedAccount.setProductId(productId);
//        generatedAccount.setReserved(true);
//        generatedAccount.setReservedBy(username);
//        generatedAccount.setReservationEndsOn(calculateReservationEndTime(maxReservationTime));
//        generatedAccount.setAccountNumber(accountNumber);
//        generatedAccountRepository.save(generatedAccount);
//
//        return accountNumber;
//
//    }
//
//
//    private AccountNumberSequenceEntity createAndGetAccountNumberSequence(
//            long productId, long branchId) {
//        AccountNumberSequenceEntity newSequence = new AccountNumberSequenceEntity();
//        newSequence.setId(accountNumberSequenceRepository.maxId() + 1);
//        newSequence.setBranchId(branchId);
//        newSequence.setProductId(productId);
//        newSequence.setSequence(1L);
//        accountNumberSequenceRepository.save(newSequence);
//        return accountNumberSequenceRepository.findOneByBranchIdAndProductId(branchId,
// productId).get();
//    }
//
//
//    public int calculateCheckDigit(String accountNumber) {
//
//        int sum = 0;
//        int length = accountNumber.length();
//
//        for (int i = 0; i < length; i++) {
//            // get digits
//            int digit = Integer.parseInt(accountNumber.substring(i, i + 1));
//            // every 2nd number multiply with 2
//            if (i % 2 == 1) {
//                digit *= 2;
//            }
//            sum += digit > 9 ? digit - 9 : digit;
//        }
//        int modulusOfSum = sum % 10;
//        int checkDigit = modulusOfSum == 0 ? 9 : 10 - modulusOfSum;
//        return checkDigit;
//    }
//
//
//
// }
