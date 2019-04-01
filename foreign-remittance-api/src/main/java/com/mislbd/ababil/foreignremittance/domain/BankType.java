package com.mislbd.ababil.foreignremittance.domain;

public class BankType {

    private Long id;

    private String name;

    private String description;

    public Long getId() {
        return id;
    }

    public BankType setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BankType setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BankType setDescription(String description) {
        this.description = description;
        return this;
    }
}
