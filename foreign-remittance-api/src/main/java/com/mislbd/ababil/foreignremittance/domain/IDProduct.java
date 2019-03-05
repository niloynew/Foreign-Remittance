package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.ababil.asset.annotation.ProductCode;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class IDProduct implements Serializable {

  private long id;

  @Pattern(regexp = "^[\\p{L} .'-]+$", message = "IDProduct name format is not valid")
  @NotBlank(message = "IDProduct name is required")
  private String name;

  @ProductCode
  @NotNull(message = "Code is required")
  private String code;

  @Positive
  @NotNull(message = "General ledger is required")
  private long generalLedgerId;

  @NotNull(message = "Currency is required")
  private List<String> currencies;

  // region <Getter and Setter>

  public long getId() {
    return id;
  }

  public IDProduct setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public IDProduct setName(String name) {
    this.name = name;
    return this;
  }

  public String getCode() {
    return code;
  }

  public IDProduct setCode(String code) {
    this.code = code;
    return this;
  }

  public long getGeneralLedgerId() {
    return generalLedgerId;
  }

  public IDProduct setGeneralLedgerId(long generalLedgerId) {
    this.generalLedgerId = generalLedgerId;
    return this;
  }

  public List<String> getCurrencies() {
    return currencies;
  }

  public IDProduct setCurrencies(List<String> currencies) {
    this.currencies = currencies;
    return this;
  }
  // endregion
}
