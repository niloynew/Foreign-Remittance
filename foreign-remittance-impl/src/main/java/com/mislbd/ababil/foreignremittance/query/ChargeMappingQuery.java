package com.mislbd.ababil.foreignremittance.query;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.asset.query.api.QueryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChargeMappingQuery extends QueryRequest {

  private Pageable pageable;
  private boolean asPage;
  private RemittanceType remittanceType;
  private Long typeId;
  private Long chargeId;
  private Boolean chargeModifiable;
  private Boolean vatModifiable;
}
