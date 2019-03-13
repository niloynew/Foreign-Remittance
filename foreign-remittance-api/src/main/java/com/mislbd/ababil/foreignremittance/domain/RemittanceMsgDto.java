package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class RemittanceMsgDto {
    private long id;
    private String lcNumber;
    private String relatedReference;
    private LocalDate valueDate;
    private String currency;
    private BigDecimal amount;
    private String instructedCurrency;
    private BigDecimal instructedAmount;
    private String messageType;
    private String orderingCustomerAcc;
    private String orderingCustomerNameAndAddress;
    private String orderingCustomerBic;
    private String orderingInsAcc;
    private String orderingInsNameAndAddress;
    private String orderingInsBic;
    private String SendingInsAcc;
    private String sendingInsNameAndAddress;
    private String sendingInsBic;
    private String sendersCorrespondentAcc;
    private String sendersCorrespondentNameAndAddress;
    private String sendersCorrespondentBic;
    private String receiversCorrespondentAcc;
    private String receiversCorrespondentNameAndAddress;
    private String receiversCorrespondentBic;
    private String intermediaryAcc;
    private String intermediaryNameAndAddress;
    private String intermediaryBic;
    private String remittanceInfo;
    private String msg;
    private String accountWithInstitutionAcc;
    private String accountWithInstitutionNameAndAddress;
    private String accountWithInstitutionBic;


    public long getId() {
        return id;
    }

    public RemittanceMsgDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getLcNumber() {
        return lcNumber;
    }

    public RemittanceMsgDto setLcNumber(String lcNumber) {
        this.lcNumber = lcNumber;
        return this;
    }

    public String getRelatedReference() {
        return relatedReference;
    }

    public RemittanceMsgDto setRelatedReference(String relatedReference) {
        this.relatedReference = relatedReference;
        return this;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public RemittanceMsgDto setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public RemittanceMsgDto setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public RemittanceMsgDto setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getInstructedCurrency() {
        return instructedCurrency;
    }

    public RemittanceMsgDto setInstructedCurrency(String instructedCurrency) {
        this.instructedCurrency = instructedCurrency;
        return this;
    }

    public BigDecimal getInstructedAmount() {
        return instructedAmount;
    }

    public RemittanceMsgDto setInstructedAmount(BigDecimal instructedAmount) {
        this.instructedAmount = instructedAmount;
        return this;
    }

    public String getMessageType() {
        return messageType;
    }

    public RemittanceMsgDto setMessageType(String messageType) {
        this.messageType = messageType;
        return this;
    }

    public String getOrderingCustomerAcc() {
        return orderingCustomerAcc;
    }

    public RemittanceMsgDto setOrderingCustomerAcc(String orderingCustomerAcc) {
        this.orderingCustomerAcc = orderingCustomerAcc;
        return this;
    }

    public String getOrderingCustomerNameAndAddress() {
        return orderingCustomerNameAndAddress;
    }

    public RemittanceMsgDto setOrderingCustomerNameAndAddress(String orderingCustomerNameAndAddress) {
        this.orderingCustomerNameAndAddress = orderingCustomerNameAndAddress;
        return this;
    }

    public String getOrderingCustomerBic() {
        return orderingCustomerBic;
    }

    public RemittanceMsgDto setOrderingCustomerBic(String orderingCustomerBic) {
        this.orderingCustomerBic = orderingCustomerBic;
        return this;
    }

    public String getOrderingInsAcc() {
        return orderingInsAcc;
    }

    public RemittanceMsgDto setOrderingInsAcc(String orderingInsAcc) {
        this.orderingInsAcc = orderingInsAcc;
        return this;
    }

    public String getOrderingInsNameAndAddress() {
        return orderingInsNameAndAddress;
    }

    public RemittanceMsgDto setOrderingInsNameAndAddress(String orderingInsNameAndAddress) {
        this.orderingInsNameAndAddress = orderingInsNameAndAddress;
        return this;
    }

    public String getOrderingInsBic() {
        return orderingInsBic;
    }

    public RemittanceMsgDto setOrderingInsBic(String orderingInsBic) {
        this.orderingInsBic = orderingInsBic;
        return this;
    }

    public String getSendingInsAcc() {
        return SendingInsAcc;
    }

    public RemittanceMsgDto setSendingInsAcc(String sendingInsAcc) {
        SendingInsAcc = sendingInsAcc;
        return this;
    }

    public String getSendingInsNameAndAddress() {
        return sendingInsNameAndAddress;
    }

    public RemittanceMsgDto setSendingInsNameAndAddress(String sendingInsNameAndAddress) {
        this.sendingInsNameAndAddress = sendingInsNameAndAddress;
        return this;
    }

    public String getSendingInsBic() {
        return sendingInsBic;
    }

    public RemittanceMsgDto setSendingInsBic(String sendingInsBic) {
        this.sendingInsBic = sendingInsBic;
        return this;
    }

    public String getSendersCorrespondentAcc() {
        return sendersCorrespondentAcc;
    }

    public RemittanceMsgDto setSendersCorrespondentAcc(String sendersCorrespondentAcc) {
        this.sendersCorrespondentAcc = sendersCorrespondentAcc;
        return this;
    }

    public String getSendersCorrespondentNameAndAddress() {
        return sendersCorrespondentNameAndAddress;
    }

    public RemittanceMsgDto setSendersCorrespondentNameAndAddress(String sendersCorrespondentNameAndAddress) {
        this.sendersCorrespondentNameAndAddress = sendersCorrespondentNameAndAddress;
        return this;
    }

    public String getSendersCorrespondentBic() {
        return sendersCorrespondentBic;
    }

    public RemittanceMsgDto setSendersCorrespondentBic(String sendersCorrespondentBic) {
        this.sendersCorrespondentBic = sendersCorrespondentBic;
        return this;
    }

    public String getReceiversCorrespondentAcc() {
        return receiversCorrespondentAcc;
    }

    public RemittanceMsgDto setReceiversCorrespondentAcc(String receiversCorrespondentAcc) {
        this.receiversCorrespondentAcc = receiversCorrespondentAcc;
        return this;
    }

    public String getReceiversCorrespondentNameAndAddress() {
        return receiversCorrespondentNameAndAddress;
    }

    public RemittanceMsgDto setReceiversCorrespondentNameAndAddress(String receiversCorrespondentNameAndAddress) {
        this.receiversCorrespondentNameAndAddress = receiversCorrespondentNameAndAddress;
        return this;
    }

    public String getReceiversCorrespondentBic() {
        return receiversCorrespondentBic;
    }

    public RemittanceMsgDto setReceiversCorrespondentBic(String receiversCorrespondentBic) {
        this.receiversCorrespondentBic = receiversCorrespondentBic;
        return this;
    }

    public String getIntermediaryAcc() {
        return intermediaryAcc;
    }

    public RemittanceMsgDto setIntermediaryAcc(String intermediaryAcc) {
        this.intermediaryAcc = intermediaryAcc;
        return this;
    }

    public String getIntermediaryNameAndAddress() {
        return intermediaryNameAndAddress;
    }

    public RemittanceMsgDto setIntermediaryNameAndAddress(String intermediaryNameAndAddress) {
        this.intermediaryNameAndAddress = intermediaryNameAndAddress;
        return this;
    }

    public String getIntermediaryBic() {
        return intermediaryBic;
    }

    public RemittanceMsgDto setIntermediaryBic(String intermediaryBic) {
        this.intermediaryBic = intermediaryBic;
        return this;
    }

    public String getRemittanceInfo() {
        return remittanceInfo;
    }

    public RemittanceMsgDto setRemittanceInfo(String remittanceInfo) {
        this.remittanceInfo = remittanceInfo;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RemittanceMsgDto setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getAccountWithInstitutionAcc() {
        return accountWithInstitutionAcc;
    }

    public RemittanceMsgDto setAccountWithInstitutionAcc(String accountWithInstitutionAcc) {
        this.accountWithInstitutionAcc = accountWithInstitutionAcc;
        return this;
    }

    public String getAccountWithInstitutionNameAndAddress() {
        return accountWithInstitutionNameAndAddress;
    }

    public RemittanceMsgDto setAccountWithInstitutionNameAndAddress(String accountWithInstitutionNameAndAddress) {
        this.accountWithInstitutionNameAndAddress = accountWithInstitutionNameAndAddress;
        return this;
    }

    public String getAccountWithInstitutionBic() {
        return accountWithInstitutionBic;
    }

    public RemittanceMsgDto setAccountWithInstitutionBic(String accountWithInstitutionBic) {
        this.accountWithInstitutionBic = accountWithInstitutionBic;
        return this;
    }
}
