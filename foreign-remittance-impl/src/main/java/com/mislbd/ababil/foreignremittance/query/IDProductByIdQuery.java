package com.mislbd.ababil.foreignremittance.query;

import com.mislbd.asset.query.api.QueryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IDProductByIdQuery extends QueryRequest {
  private Long id;
}
