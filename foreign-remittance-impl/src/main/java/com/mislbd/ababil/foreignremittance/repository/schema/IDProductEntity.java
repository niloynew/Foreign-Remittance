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

    private long generalLedgerId;

    // ### This part is for mapping with other entities ###//

    @OneToMany(mappedBy = "product")
    private List<NostroAccountEntity> nostroAccounts;

    @OneToMany(mappedBy = "product")
    private List<ShadowAccountEntity> shadowAccounts;

    // region <Getter and Setter>

    public long getId() {
        return id;
    }

    public IDProductEntity setId(long id) {
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

    public long getGeneralLedgerId() {
        return generalLedgerId;
    }

    public IDProductEntity setGeneralLedgerId(long generalLedgerId) {
        this.generalLedgerId = generalLedgerId;
        return this;
    }

    public List<NostroAccountEntity> getNostroAccounts() {
        return nostroAccounts;
    }

    public void setNostroAccounts(List<NostroAccountEntity> nostroAccounts) {
        this.nostroAccounts = nostroAccounts;
    }

    public List<ShadowAccountEntity> getShadowAccounts() {
        return shadowAccounts;
    }

    public void setShadowAccounts(List<ShadowAccountEntity> shadowAccounts) {
        this.shadowAccounts = shadowAccounts;
    }
}
