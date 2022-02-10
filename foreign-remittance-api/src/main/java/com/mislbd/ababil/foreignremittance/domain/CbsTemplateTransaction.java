package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.transaction.api.transaction.model.CbsTransaction;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CbsTemplateTransaction extends CbsTransaction {
    private Long adjReferenceId;
}
