package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.OtherCbsSystemSettlementStatus;
import com.mislbd.ababil.foreignremittance.domain.ShadowTransactionRecord;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ShadowTransactionRecordService {

  PagedResult<ShadowTransactionRecord> getAccountStatements(
      Pageable pageable, Long accountId, LocalDate fromDate, LocalDate toDate);

  List<ShadowTransactionRecord> getAccountStatements(
      Long accountId, LocalDate fromDate, LocalDate toDate);

  PagedResult<ShadowTransactionRecord> getAccountStatement(
      Pageable pageable, String shadowAccountNumber, LocalDate fromDate, LocalDate toDate);

  PagedResult<ShadowTransactionRecord> getUnreconciledTransactionData(
      Pageable pageable,
      String accountNumber,
      LocalDate fromDate,
      LocalDate toDate,
      OtherCbsSystemSettlementStatus reconcileStatus);
}
