package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.PaymentPurpose;
import com.mislbd.ababil.foreignremittance.mapper.PaymentPurposeMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.PaymentPurposeRepository;
import com.mislbd.ababil.foreignremittance.repository.specification.PaymentPurposeSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentPurposeServiceImpl implements PaymentPurposeService {

  private final PaymentPurposeRepository paymentPurposeRepository;
  private final PaymentPurposeMapper paymentPurposeMapper;

  public PaymentPurposeServiceImpl(
      PaymentPurposeRepository paymentPurposeRepository,
      PaymentPurposeMapper paymentPurposeMapper) {
    this.paymentPurposeRepository = paymentPurposeRepository;
    this.paymentPurposeMapper = paymentPurposeMapper;
  }

  @Override
  public PagedResult<PaymentPurpose> getPaymentPurposes(
      Pageable pageable, Long id, String code, String description) {
    return PagedResultBuilder.build(
        paymentPurposeRepository.findAll(
            PaymentPurposeSpecification.findPaymentPurpose(id, code, description), pageable),
        paymentPurposeMapper.entityToDomain());
  }

  @Override
  public List<PaymentPurpose> getPaymentPurposes(Long id, String code, String description) {
    return ListResultBuilder.build(
        paymentPurposeRepository.findAll(
            PaymentPurposeSpecification.findPaymentPurpose(id, code, description)),
        paymentPurposeMapper.entityToDomain());
  }
}
