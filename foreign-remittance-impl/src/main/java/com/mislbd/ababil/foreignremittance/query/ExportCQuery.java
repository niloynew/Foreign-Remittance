package com.mislbd.ababil.foreignremittance.query;

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
public class ExportCQuery extends QueryRequest {

  private boolean asPage;
  private Pageable pageable;
  private String name;
  private String ownerName;
  private String address;
  private String country;
  private String cpName;
  private String cpEmail;
}
