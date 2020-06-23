package com.mislbd.ababil.foreignremittance.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors
public class NostroReconcileDtoBrokerList {
  private List<NostroReconcileDtoBroker> nostroReconcileDtoBrokerList;
}
