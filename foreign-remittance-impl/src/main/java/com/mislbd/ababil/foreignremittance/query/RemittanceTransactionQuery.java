package com.mislbd.ababil.foreignremittance.query;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransactionStatus;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.asset.query.api.QueryRequest;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RemittanceTransactionQuery extends QueryRequest {
  private boolean asPage;
  private Pageable pageable;
  private RemittanceType remittanceType;
  private String transactionReferenceNumber;
  private String applicantName;
  private String beneficiaryName;
  private LocalDate fromDate;
  private LocalDate toDate;
  private RemittanceTransactionStatus status;
}
