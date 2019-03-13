package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.domain.Status;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Remittance_Transaction")
public class RemittanceTransactionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "REMITTANCE_TRANSACTION_SEQUENCE_GEN")
    @SequenceGenerator(
            name = "REMITTANCE_TRANSACTION_SEQUENCE_GEN",
            allocationSize = 1,
            sequenceName = SchemaConstant.REMITTANCE_TRANSACTION_SEQUENCE)
    @Column(name = "ID")
    private long id;

    @Column(name = "TRANSACTION_TYPE")
    private Long transactionTypeId;

    @Column(name = "REMITTANCE_TYPE")
    private RemittanceType remittanceType;

    @Column(name = "OPERATION_ID")
    private Long operationId;

    @Column(name = "PAYMENT_PURPOSE_ID")
    private Long paymentPurposeId;

    @Column(name = "COMMODITY_DESCRIPTION")
    private String commodityDescription;

    @Column(name = "TRANSACTION_REFERENCE_NUMBER")
    private Long transactionReferenceNumber;

    @Column(name = "INSTRUCTION_NUMBER")
    private Long instructionNumber;

    @Column(name = "CB_FUND_SOURCE")
    private Long cbFundSourceId;

    @Column(name = "DELIVERY_TERM")
    private String deliveryTerm;

    // ##### General Information #####//
    @Column(name = "APPLICANT_ID")
    private Long applicantId;

    @Column(name = "APPLICANT_ACC_NUMBER")
    private String applicantAccountNumber;

    @Column(name = "BENEFICIARY_NUMBER")
    private Long beneficiaryId;

    @Column(name = "BENEFICIARY_ACC_NUMBER")
    private String beneficiaryAccountNumber;

    @Column(name = "B2B_INFORMATION")
    private String b2bInformation;

    // ##### Banks Information #####//

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "remittanceTransaction")
    private List<BankInformationEntity> bankInformationEntity;

    // ##### Financial Information #####//
    @Column(name = "DEBIT_ACC_TYPE")
    private Long debitAccountTypeId;

    @Column(name = "DEBIT_ACC_NUMBER")
    private String debitAccountNumber;

    @Column(name = "CREDIT_ACC_TYPE")
    private Long creditAccountTypeId;

    @Column(name = "CREDIT_ACC_NUMBER")
    private String creditAccountNumber;

    @Column(name = "CURRENCY_CODE")
    private String currencyCode;

    @Column(name = "CLIENT_RATE_TYPE")
    private Long clientRateTypeId;

    @Column(name = "HO_RATE_TYPE")
    private Long hoRateTypeId;

    @Column(name = "AMOUNT_FCY")
    private BigDecimal amountFcy;

    @Column(name = "AMOUNT_LCY")
    private BigDecimal amountLcy;

    @Column(name = "EXCHANGE_GAIN")
    private BigDecimal exchangeGain;

    @Column(name = "STATUS")
    private Status status;

    public long getId() {
        return id;
    }

    public RemittanceTransactionEntity setId(long id) {
        this.id = id;
        return this;
    }

    public Long getTransactionTypeId() {
        return transactionTypeId;
    }

    public RemittanceTransactionEntity setTransactionTypeId(Long transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
        return this;
    }

    public RemittanceType getRemittanceType() {
        return remittanceType;
    }

    public RemittanceTransactionEntity setRemittanceType(RemittanceType remittanceType) {
        this.remittanceType = remittanceType;
        return this;
    }

    public Long getOperationId() {
        return operationId;
    }

    public RemittanceTransactionEntity setOperationId(Long operationId) {
        this.operationId = operationId;
        return this;
    }

    public Long getPaymentPurposeId() {
        return paymentPurposeId;
    }

    public RemittanceTransactionEntity setPaymentPurposeId(Long paymentPurposeId) {
        this.paymentPurposeId = paymentPurposeId;
        return this;
    }

    public String getCommodityDescription() {
        return commodityDescription;
    }

    public RemittanceTransactionEntity setCommodityDescription(String commodityDescription) {
        this.commodityDescription = commodityDescription;
        return this;
    }

    public Long getTransactionReferenceNumber() {
        return transactionReferenceNumber;
    }

    public RemittanceTransactionEntity setTransactionReferenceNumber(
            Long transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
        return this;
    }

    public Long getInstructionNumber() {
        return instructionNumber;
    }

    public RemittanceTransactionEntity setInstructionNumber(Long instructionNumber) {
        this.instructionNumber = instructionNumber;
        return this;
    }

    public Long getCbFundSourceId() {
        return cbFundSourceId;
    }

    public RemittanceTransactionEntity setCbFundSourceId(Long cbFundSourceId) {
        this.cbFundSourceId = cbFundSourceId;
        return this;
    }

    public String getDeliveryTerm() {
        return deliveryTerm;
    }

    public RemittanceTransactionEntity setDeliveryTerm(String deliveryTerm) {
        this.deliveryTerm = deliveryTerm;
        return this;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public RemittanceTransactionEntity setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
        return this;
    }

    public String getApplicantAccountNumber() {
        return applicantAccountNumber;
    }

    public RemittanceTransactionEntity setApplicantAccountNumber(String applicantAccountNumber) {
        this.applicantAccountNumber = applicantAccountNumber;
        return this;
    }

    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public RemittanceTransactionEntity setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
        return this;
    }

    public String getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    public RemittanceTransactionEntity setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
        return this;
    }

    public List<BankInformationEntity> getBankInformationEntity() {
        return bankInformationEntity;
    }

    public RemittanceTransactionEntity setBankInformationEntity(List<BankInformationEntity> bankInformationEntity) {
        this.bankInformationEntity = bankInformationEntity;
        return this;
    }

    public Long getDebitAccountTypeId() {
        return debitAccountTypeId;
    }

    public RemittanceTransactionEntity setDebitAccountTypeId(Long debitAccountTypeId) {
        this.debitAccountTypeId = debitAccountTypeId;
        return this;
    }

    public String getDebitAccountNumber() {
        return debitAccountNumber;
    }

    public RemittanceTransactionEntity setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
        return this;
    }

    public Long getCreditAccountTypeId() {
        return creditAccountTypeId;
    }

    public RemittanceTransactionEntity setCreditAccountTypeId(Long creditAccountTypeId) {
        this.creditAccountTypeId = creditAccountTypeId;
        return this;
    }

    public String getCreditAccountNumber() {
        return creditAccountNumber;
    }

    public RemittanceTransactionEntity setCreditAccountNumber(String creditAccountNumber) {
        this.creditAccountNumber = creditAccountNumber;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public RemittanceTransactionEntity setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public Long getClientRateTypeId() {
        return clientRateTypeId;
    }

    public RemittanceTransactionEntity setClientRateTypeId(Long clientRateTypeId) {
        this.clientRateTypeId = clientRateTypeId;
        return this;
    }

    public Long getHoRateTypeId() {
        return hoRateTypeId;
    }

    public RemittanceTransactionEntity setHoRateTypeId(Long hoRateTypeId) {
        this.hoRateTypeId = hoRateTypeId;
        return this;
    }

    public BigDecimal getAmountFcy() {
        return amountFcy;
    }

    public RemittanceTransactionEntity setAmountFcy(BigDecimal amountFcy) {
        this.amountFcy = amountFcy;
        return this;
    }

    public BigDecimal getAmountLcy() {
        return amountLcy;
    }

    public RemittanceTransactionEntity setAmountLcy(BigDecimal amountLcy) {
        this.amountLcy = amountLcy;
        return this;
    }

    public BigDecimal getExchangeGain() {
        return exchangeGain;
    }

    public RemittanceTransactionEntity setExchangeGain(BigDecimal exchangeGain) {
        this.exchangeGain = exchangeGain;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public RemittanceTransactionEntity setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getB2bInformation() {
        return b2bInformation;
    }

    public RemittanceTransactionEntity setB2bInformation(String b2bInformation) {
        this.b2bInformation = b2bInformation;
        return this;
    }
}
