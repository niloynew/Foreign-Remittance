package com.mislbd.ababil.foreignremittance.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class NostroReconcileDtoList {
  private List<NostroReconcileDto> nostroReconcileDtoList;
}
