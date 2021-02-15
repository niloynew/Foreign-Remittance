package com.mislbd.ababil.foreignremittance.query;

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
public class AccountQuery extends QueryRequest {

  Pageable pageable;
  private boolean asPage;
  private String number;
  private String name;
  private String nostroAccountNumber;
  private String bank;
  private String branch;
  private LocalDate accountOpenDate;
  private String currency;
  private String product;
  private Boolean isActive;
}
