package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.ababil.asset.annotation.ProductCode;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class IDProduct implements Serializable {

  private long id;

  @Pattern(regexp = "^[\\p{L} .'-]+$", message = "IDProduct name format is not valid")
  @NotBlank(message = "IDProduct name is required")
  private String name;

  @ProductCode
  @NotNull(message = "Code is required")
  private String code;

  private Long productGLId;

  private String productGLCode;

  //  @Positive
  //  @NotNull(message = "Exchange Gain General ledger is required")
  private Long exchangeGainGLId;

  private String exchangeGainGLCode;

  //  @NotNull(message = "Currency is required")
  private List<String> currencies;

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

  public Long getProductGLId() {
    return productGLId;
  }

  public IDProduct setProductGLId(Long productGLId) {
    this.productGLId = productGLId;
    return this;
  }

  public String getProductGLCode() {
    return productGLCode;
  }

  public IDProduct setProductGLCode(String productGLCode) {
    this.productGLCode = productGLCode;
    return this;
  }

  public Long getExchangeGainGLId() {
    return exchangeGainGLId;
  }

  public IDProduct setExchangeGainGLId(Long exchangeGainGLId) {
    this.exchangeGainGLId = exchangeGainGLId;
    return this;
  }

  public String getExchangeGainGLCode() {
    return exchangeGainGLCode;
  }

  public IDProduct setExchangeGainGLCode(String exchangeGainGLCode) {
    this.exchangeGainGLCode = exchangeGainGLCode;
    return this;
  }

  public List<String> getCurrencies() {
    return currencies;
  }

  public IDProduct setCurrencies(List<String> currencies) {
    this.currencies = currencies;
    return this;
  }
}
