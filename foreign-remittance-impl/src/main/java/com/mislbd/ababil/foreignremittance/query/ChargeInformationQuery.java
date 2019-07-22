package com.mislbd.ababil.foreignremittance.query;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.asset.query.api.QueryRequest;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChargeInformationQuery extends QueryRequest {
  private RemittanceType remittanceType;
  private Long typeId;
  private String accountNumber;
  private BigDecimal amount;
}
