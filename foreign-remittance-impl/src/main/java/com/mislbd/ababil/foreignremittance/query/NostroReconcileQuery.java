package com.mislbd.ababil.foreignremittance.query;

import com.mislbd.asset.query.api.QueryRequest;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NostroReconcileQuery extends QueryRequest {
  private Pageable pageable;
  private boolean asPage;
  private Long id;
  String advBranch;
  private String accountNo;
  private boolean selected;
  private LocalDate valueDate;
}
