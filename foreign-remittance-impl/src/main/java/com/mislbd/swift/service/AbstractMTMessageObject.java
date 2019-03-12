package com.mislbd.swift.service;

import com.fasterxml.jackson.annotation.*;
import com.mislbd.asset.commons.data.domain.Model;
import com.mislbd.swift.service.mt103.SingleCustomerCreditTransferGeneral;
import com.mislbd.swift.service.mt202.GeneralFinancialInstitutionTransfer;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "clazz")
@JsonSubTypes({
  @JsonSubTypes.Type(
      value = GeneralFinancialInstitutionTransfer.class,
      name = "GeneralFinancialInstitutionTransfer"),
  @JsonSubTypes.Type(
      value = SingleCustomerCreditTransferGeneral.class,
      name = "SingleCustomerCreditTransferGeneral"),
  /*@JsonSubTypes.Type(value = SingleCustomerCreditTransferREMIT.class, name = "SingleCustomerCreditTransferREMIT"),
          @JsonSubTypes.Type(value = SingleCustomerCreditTransferExt.class, name = "SingleCustomerCreditTransferExt"),
          @JsonSubTypes.Type(value = ForeignExchangeConfirmation.class, name = "ForeignExchangeConfirmation"),
          @JsonSubTypes.Type(value = AdviceOfPayment.class, name = "AdviceOfPayment"),
          @JsonSubTypes.Type(value = Acknowledgement410.class, name = "Acknowledgement410"),
          @JsonSubTypes.Type(value = AdviceOfAcceptance.class, name = "AdviceOfAcceptance"),
          @JsonSubTypes.Type(value = AdviceOfNonPaymentNonAcceptance.class, name = "AdviceOfNonPaymentNonAcceptance"),
          @JsonSubTypes.Type(value = Tracer.class, name = "Tracer"),
          @JsonSubTypes.Type(value = IssueOfADocumentaryCredit.class, name = "IssueOfADocumentaryCredit"),
          @JsonSubTypes.Type(value = AmendmentToADocumentaryCredit.class, name = "AmendmentToADocumentaryCredit"),
          @JsonSubTypes.Type(value = Acknowledgement730.class, name = "Acknowledgement730"),
          @JsonSubTypes.Type(value = AdviceOfRefusal.class, name = "AdviceOfRefusal"),
          @JsonSubTypes.Type(value = AdviceOfDiscrepancy.class, name = "AdviceOfDiscrepancy"),
          @JsonSubTypes.Type(value = AuthorisationToPayAcceptOrNegotiate.class, name = "AuthorisationToPayAcceptOrNegotiate"),
          @JsonSubTypes.Type(value = AdviceOfPaymentAcceptanceNegotiation.class, name = "AdviceOfPaymentAcceptanceNegotiation"),
          @JsonSubTypes.Type(value = AdviceOfReimbursementOrPayment.class, name = "AdviceOfReimbursementOrPayment"),
          @JsonSubTypes.Type(value = GuaranteeStandbyLetterOfCredit.class, name = "GuaranteeStandbyLetterOfCredit"),
          @JsonSubTypes.Type(value = GuaranteeStandbyLetterOfCreditAmendment.class, name = "GuaranteeStandbyLetterOfCreditAmendment"),
          @JsonSubTypes.Type(value = AcknowledgementOfAGuaranteeStandbyMessage.class, name = "AcknowledgementOfAGuaranteeStandbyMessage"),
          @JsonSubTypes.Type(value = FreeFormatMessage799.class, name = "FreeFormatMessage799"),
          @JsonSubTypes.Type(value = Queries795.class, name = "Queries795"),
          @JsonSubTypes.Type(value = Answers796.class, name = "Answers796"),
          @JsonSubTypes.Type(value = FreeFormat199.class, name = "FreeFormat199"),
          @JsonSubTypes.Type(value = FreeFormat299.class, name = "FreeFormat299"),
          @JsonSubTypes.Type(value = GeneralFinInsTransCOV.class, name = "GeneralFinInsTransCOV"),
          @JsonSubTypes.Type(value = FreeFormat399.class, name = "FreeFormat399"),
          @JsonSubTypes.Type(value = FreeFormatMessage999.class, name = "FreeFormatMessage999"),
          @JsonSubTypes.Type(value = StatementMessage.class, name = "StatementMessage"),
          @JsonSubTypes.Type(value = ConfirmationOfCredit.class, name = "ConfirmationOfCredit"),
          @JsonSubTypes.Type(value = ConfirmationOfDebit.class, name = "ConfirmationOfDebit"),
          @JsonSubTypes.Type(value = CustomerStatementMessgae.class, name = "CustomerStatementMessgae"),
          @JsonSubTypes.Type(value = RequestForCancellation792.class, name = "RequestForCancellation792"),
          @JsonSubTypes.Type(value = AdviceOfDischarge.class, name = "AdviceOfDischarge")
  */
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
/*
@JsonDeserialize(as = GeneralFinancialInstitutionTransfer.class
                  )
*/

public abstract class AbstractMTMessageObject implements Model {
  @Id @GeneratedValue private long id;

  protected String referenceNumber;
  @ManyToOne @JoinColumn private BicList receiverAddress;
  protected String entryUser;
  protected String entryTerminal;
  protected Date entryDateTime;
  protected String status;
  protected String modifyUser;
  protected String verifierTerminal;
  protected Date modificationDateTime;
  protected String rejectReason;
  protected String messageType;
  protected String field20;
  @ManyToOne @JoinColumn private BicList senderAddress;
  private boolean directionIncoming;
  @Lob private String textBlock;
  private String deliveryStatus;
  private String messagePriority;
  private String messageInputReference;
  private String messageOutputReference;
  private Date fileLastModified;
  private Date valueDate;
  private BigDecimal amount;
  private String field21;
  private Boolean comingFromCBS;
  private String field25;
  @Transient private AddtionalDomainInfo addtionalDomainInfo;
  private String mur;
  @Transient private Long ackNakId;

  @JsonProperty private String clazz = this.getClass().getSimpleName();

  public String getDeliveryStatus() {
    return deliveryStatus;
  }

  public void setDeliveryStatus(String deliveryStatus) {
    this.deliveryStatus = deliveryStatus;
  }

  public String getMessagePriority() {
    return messagePriority;
  }

  public void setMessagePriority(String messagePriority) {
    this.messagePriority = messagePriority;
  }

  @JsonIgnore
  public abstract String getType();

  @JsonIgnore
  public abstract void nullifiyChildren();

  public void loadLazyChildren() {}

  public String getEntryUser() {
    return entryUser;
  }

  public void setEntryUser(String entryUser) {
    this.entryUser = entryUser;
  }

  public String getEntryTerminal() {
    return entryTerminal;
  }

  public void setEntryTerminal(String entryTerminal) {
    this.entryTerminal = entryTerminal;
  }

  public Date getEntryDateTime() {
    return entryDateTime;
  }

  public void setEntryDateTime(Date entryDateTime) {
    this.entryDateTime = entryDateTime == null ? new Date() : entryDateTime;
  }

  public String getReferenceNumber() {
    return referenceNumber;
  }

  public void setReferenceNumber(String referenceNumber) {
    this.referenceNumber = referenceNumber;
  }

  public String getMessageType() {
    return getType();
  }

  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }

  public String getModifyUser() {
    return modifyUser;
  }

  public void setModifyUser(String modifyUser) {
    this.modifyUser = modifyUser;
  }

  public Date getModificationDateTime() {
    return modificationDateTime;
  }

  public void setModificationDateTime(Date modificationDateTime) {
    this.modificationDateTime = modificationDateTime == null ? new Date() : modificationDateTime;
  }

  public String getVerifierTerminal() {
    return verifierTerminal;
  }

  public void setVerifierTerminal(String verifierTerminal) {
    this.verifierTerminal = verifierTerminal;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getRejectReason() {
    return rejectReason;
  }

  public void setRejectReason(String rejectReason) {
    this.rejectReason = rejectReason;
  }

  public String getField20() {
    return field20;
  }

  public void setField20(String field20) {
    this.field20 = field20;
  }
  /*
  public String getReceiverAddress() {
  	return receiverAddress;
  }

  public void setReceiverAddress(String receiverAddress) {
  	this.receiverAddress = receiverAddress;
  }*/

  public AddtionalDomainInfo getAddtionalDomainInfo() {
    return addtionalDomainInfo;
  }

  public void setAddtionalDomainInfo(AddtionalDomainInfo addtionalDomainInfo) {
    this.addtionalDomainInfo = addtionalDomainInfo;
  }

  public BicList getReceiverAddress() {
    return receiverAddress;
  }

  public void setReceiverAddress(BicList receiverAddress) {
    this.receiverAddress = receiverAddress;
  }

  public BicList getSenderAddress() {
    return senderAddress;
  }

  public void setSenderAddress(BicList senderAddress) {
    this.senderAddress = senderAddress;
  }

  public Date getValueDate() {
    return valueDate;
  }

  public String getField21() {
    return field21;
  }

  public void setField21(String field21) {
    this.field21 = field21;
  }

  public void setValueDate(Date valueDate) {
    this.valueDate = valueDate;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getTextBlock() {
    return textBlock;
  }

  public void setTextBlock(String textBlock) {
    this.textBlock = textBlock;
  }

  public boolean isDirectionIncoming() {
    return directionIncoming;
  }

  public void setDirectionIncoming(boolean directionIncoming) {
    this.directionIncoming = directionIncoming;
  }

  public String getMessageInputReference() {
    return messageInputReference;
  }

  public void setMessageInputReference(String messageInputReference) {
    this.messageInputReference = messageInputReference;
  }

  public String getMessageOutputReference() {
    return messageOutputReference;
  }

  public void setMessageOutputReference(String messageOutputReference) {
    this.messageOutputReference = messageOutputReference;
  }

  public Date getFileLastModified() {
    return fileLastModified;
  }

  public void setFileLastModified(Date fileLastModified) {
    this.fileLastModified = fileLastModified;
  }

  public Boolean isComingFromCBS() {
    return comingFromCBS;
  }

  public void setComingFromCBS(Boolean comingFromCBS) {
    this.comingFromCBS = comingFromCBS;
  }

  public String getField25() {
    return field25;
  }

  public void setField25(String field25) {
    this.field25 = field25;
  }

  public String getMur() {
    return mur;
  }

  public void setMur(String mur) {
    this.mur = mur;
  }

  public Long getAckNakId() {
    return ackNakId;
  }

  public void setAckNakId(Long ackNakId) {
    this.ackNakId = ackNakId;
  }

  public String getClazz() {
    return clazz;
  }

  public void setClazz(String clazz) {
    this.clazz = clazz;
  }

  @PrePersist
  public void onPrePersist() {
    if (this.isComingFromCBS() == null) {
      this.setComingFromCBS(false);
    }
  }

  @PreUpdate
  public void onPreUpdate() {
    if (this.isComingFromCBS() == null) {
      this.setComingFromCBS(false);
    }
  }
}
