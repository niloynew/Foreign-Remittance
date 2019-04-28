package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.PaymentPurpose;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface PaymentPurposeService {

  PagedResult<PaymentPurpose> getPaymentPurposes(Pageable pageable);

  List<PaymentPurpose> getPaymentPurposes();
}
