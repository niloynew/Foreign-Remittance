package com.mislbd.ababil.foreignremittance.query;

import com.mislbd.ababil.foreignremittance.domain.OtherCbsSystemSettlementStatus;
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
public class AbabilShadowTransactionQuery extends QueryRequest {
  private Pageable pageable;
  private String accountNumber;
  private LocalDate fromDate;
  private LocalDate toDate;
  private OtherCbsSystemSettlementStatus status;
}
