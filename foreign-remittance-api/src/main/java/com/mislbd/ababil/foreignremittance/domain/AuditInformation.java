package com.mislbd.ababil.foreignremittance.domain;

import java.time.LocalDate;

public class AuditInformation {

    private String entryUser;

    private String entryTerminal;

    private LocalDate entryDate;

    private String verifyUser;

    private String verifyTerminal;

    private Integer userBranch;

    private String processId;

    public String getEntryUser() {
        return entryUser;
    }

    public AuditInformation setEntryUser(String entryUser) {
        this.entryUser = entryUser;
        return this;
    }

    public String getEntryTerminal() {
        return entryTerminal;
    }

    public AuditInformation setEntryTerminal(String entryTerminal) {
        this.entryTerminal = entryTerminal;
        return this;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public AuditInformation setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
        return this;
    }

    public String getVerifyUser() {
        return verifyUser;
    }

    public AuditInformation setVerifyUser(String verifyUser) {
        this.verifyUser = verifyUser;
        return this;
    }

    public String getVerifyTerminal() {
        return verifyTerminal;
    }

    public AuditInformation setVerifyTerminal(String verifyTerminal) {
        this.verifyTerminal = verifyTerminal;
        return this;
    }

    public Integer getUserBranch() {
        return userBranch;
    }

    public AuditInformation setUserBranch(Integer userBranch) {
        this.userBranch = userBranch;
        return this;
    }

    public String getProcessId() {
        return processId;
    }

    public AuditInformation setProcessId(String processId) {
        this.processId = processId;
        return this;
    }
}
