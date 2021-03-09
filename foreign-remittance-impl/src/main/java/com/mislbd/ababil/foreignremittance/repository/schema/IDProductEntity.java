package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
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

  @OneToMany(mappedBy = "product")
  private List<NostroAccountEntity> nostroAccounts;

  @OneToMany(mappedBy = "product")
  private List<ShadowAccountEntity> shadowAccounts;
}
