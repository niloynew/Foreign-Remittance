package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.ID_PRODUCT_TABLE_NAME)
public class IDProductEntity extends BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_PRODUCT_ID_GEN")
  @SequenceGenerator(
      name = "ID_PRODUCT_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_PRODUCT_SEQUENCE_NAME)
  private Long id;

  @Column(name = "NAME", nullable = false, unique = true)
  private String name;

  @Column(name = "CODE", nullable = false, length = 3, unique = true)
  private String code;

  @ElementCollection
  @CollectionTable(
      name = SchemaConstant.ID_PRODUCT_CURRENCIES_TABLE_NAME,
      joinColumns = @JoinColumn(name = "ID", referencedColumnName = "ID"))
  @Column(name = "CURRENCY_CODE")
  private List<String> currencies;

  @Column(name = "PRODUCT_GL_ID")
  private Long productGLId;

  @Column(name = "PRODUCT_GL_CODE")
  private String productGLCode;

  @Column(name = "EXCHANGE_GL_ID")
  private Long exchangeGainGLId;

  @Column(name = "EXCHANGE_GL_CODE")
  private String exchangeGainGLCode;

  // ### This part is for mapping with other entities ###//

  @OneToMany(mappedBy = "product")
  private List<NostroAccountEntity> nostroAccounts;

  @OneToMany(mappedBy = "product")
  private List<ShadowAccountEntity> shadowAccounts;

  public Long getId() {
    return id;
  }

  public IDProductEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public IDProductEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getCode() {
    return code;
  }

  public IDProductEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public List<String> getCurrencies() {
    return currencies;
  }

  public IDProductEntity setCurrencies(List<String> currencies) {
    this.currencies = currencies;
    return this;
  }

  public Long getProductGLId() {
    return productGLId;
  }

  public IDProductEntity setProductGLId(Long productGLId) {
    this.productGLId = productGLId;
    return this;
  }

  public String getProductGLCode() {
    return productGLCode;
  }

  public IDProductEntity setProductGLCode(String productGLCode) {
    this.productGLCode = productGLCode;
    return this;
  }

  public Long getExchangeGainGLId() {
    return exchangeGainGLId;
  }

  public IDProductEntity setExchangeGainGLId(Long exchangeGainGLId) {
    this.exchangeGainGLId = exchangeGainGLId;
    return this;
  }

  public String getExchangeGainGLCode() {
    return exchangeGainGLCode;
  }

  public IDProductEntity setExchangeGainGLCode(String exchangeGainGLCode) {
    this.exchangeGainGLCode = exchangeGainGLCode;
    return this;
  }

  public List<NostroAccountEntity> getNostroAccounts() {
    return nostroAccounts;
  }

  public IDProductEntity setNostroAccounts(List<NostroAccountEntity> nostroAccounts) {
    this.nostroAccounts = nostroAccounts;
    return this;
  }

  public List<ShadowAccountEntity> getShadowAccounts() {
    return shadowAccounts;
  }

  public IDProductEntity setShadowAccounts(List<ShadowAccountEntity> shadowAccounts) {
    this.shadowAccounts = shadowAccounts;
    return this;
  }
}
