package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.transaction.api.transaction.model.CbsTransaction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CbsTemplateTransaction extends CbsTransaction {
  private Long adjReferenceId;
}
