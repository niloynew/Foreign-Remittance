package com.mislbd.ababil.foreignremittance.query;

import com.mislbd.asset.query.api.QueryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class PaymentPurposeQuery extends QueryRequest {
  private boolean asPage;
  private Pageable pageable;
  private Long id;

  private String code;
  private String description;
}
