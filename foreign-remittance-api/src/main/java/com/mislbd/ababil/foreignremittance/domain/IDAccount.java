package com.mislbd.ababil.foreignremittance.domain;

import java.time.LocalDate;

public class IDAccount {

    private long id;
    private String accountTitle;
    private String accountNumber;
    private LocalDate openDate;
    private String nostroAccountNumber;
    private String currency;
    private String accountType;
    private String bank;
    private String branch;

    // region <Getter and Setter>

    public long getId() {
        return id;
    }

    public IDAccount setId(long id) {
        this.id = id;
        return this;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public IDAccount setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
        return this;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public IDAccount setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public IDAccount setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
        return this;
    }

    public String getNostroAccountNumber() {
        return nostroAccountNumber;
    }

    public IDAccount setNostroAccountNumber(String nostroAccountNumber) {
        this.nostroAccountNumber = nostroAccountNumber;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public IDAccount setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getAccountType() {
        return accountType;
    }

    public IDAccount setAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public String getBank() {
        return bank;
    }

    public IDAccount setBank(String bank) {
        this.bank = bank;
        return this;
    }

    public String getBranch() {
        return branch;
    }

    public IDAccount setBranch(String branch) {
        this.branch = branch;
        return this;
    }
    // endregion
}
