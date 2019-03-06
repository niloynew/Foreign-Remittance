package com.mislbd.swift.service.mt202;

import com.mislbd.asset.commons.data.domain.Model;
import javax.persistence.*;

// import com.mislbd.swift.util.Constants;
// import com.mislbd.swift.util.SwiftStringUtiliy;
@Entity
public class SenderToReceiverInformation implements Model {

  @Id @GeneratedValue private long id;

  private String selectedCode = "";

  @Column(length = 700)
  private String narative = "";

  public String getNarative() {
    return narative;
  }

  public void setNarative(String narative) {
    this.narative = narative;
  }

  public String getSelectedCode() {
    return selectedCode;
  }

  public void setSelectedCode(String selectedCode) {
    this.selectedCode = selectedCode;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
