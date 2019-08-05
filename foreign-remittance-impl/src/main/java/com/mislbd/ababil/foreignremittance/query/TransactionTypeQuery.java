package com.mislbd.ababil.foreignremittance.query;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.asset.query.api.QueryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionTypeQuery extends QueryRequest {
  private Pageable pageable;
  private Long id;
  private boolean asPage;
  private RemittanceType remittanceType;
}
