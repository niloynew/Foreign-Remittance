package com.mislbd.ababil.foreignremittance.query;

import com.mislbd.ababil.foreignremittance.domain.ChargeAccountType;
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
public class ChargeConfigurationQuery extends QueryRequest {

  private Pageable pageable;
  private String chargeName;
  private ChargeAccountType chargeAccountType;
  private ChargeAccountType vatAccountType;
  private Boolean status;
  private boolean asPage;
}
