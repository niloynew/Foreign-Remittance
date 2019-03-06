package com.mislbd.swift.service.mt202;

import com.mislbd.swift.service.AbstractMTMessageObject;
import com.mislbd.swift.service.mt103.DateCurrencyAmountInfo;
import com.mislbd.swift.service.subinfo.*;
import javax.persistence.*;

@MappedSuperclass
public abstract class GeneralFinancialInsCommon extends AbstractMTMessageObject {
  private String relatedReference;
  private String transactionReferenceNumber;
  /*@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
  @Fetch(FetchMode.SUBSELECT)
  @JoinColumn(name="mt202_id")
  private List<TimeIndication> timeIndication;*/
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "valuedateCur_id")
  private DateCurrencyAmountInfo valueDateCurrencyCodeAmount;

  @Column(name = "selectedOrderingInsOption")
  private OrderingInstitutionOption selectedOrderingInstitutionOption;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "orderingInsA_id")
  private PartyInfoForOptionA orderingInstitutionOptionA;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "orderingInsD_id")
  private PartyInfoForOptionD orderingInstitutionOptionD;

  @Column(name = "selectedSenderCorresOption")
  private SenderReceiverCorrespondentOption selectedSenderCorrespondentOption;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "SenderCorsA_id")
  private PartyInfoForOptionA senderCorrespondentOptionA;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "SenderCorsB_id")
  private PartyInfoForOptionB senderCorrespondentOptionB;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "SenderCorsD_id")
  private PartyInfoForOptionD senderCorrespondentOptionD;

  @Column(name = "selectedReceiversCorresOption")
  private SenderReceiverCorrespondentOption selectedReceiversCorrespondentOption;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ReceiverCorsA_id")
  private PartyInfoForOptionA receiversCorrespondentOptionA;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ReceiverCorsB_id")
  private PartyInfoForOptionB receiversCorrespondentOptionB;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ReceiverCorsD_id")
  private PartyInfoForOptionD receiversCorrespondentOptionD;

  private IntermediaryOption selectedIntermediaryOption;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "intermediaryOptA_id")
  private PartyInfoForOptionA intermediaryOptionA;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "intermediaryOptD_id")
  private PartyInfoForOptionD intermediaryOptionD;

  @Column(name = "selectedAccWithInsOption")
  private AccountWithInstitutionOption selectedAccountWithInstitutionOption;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "accountWithInsOptA_id")
  private PartyInfoForOptionA accountWithInstitutionOptionA;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "accountWithInsOptB_id")
  private PartyInfoForOptionB accountWithInstitutionOptionB;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "accountWithInsOptD_id")
  private PartyInfoForOptionD accountWithInstitutionOptionD;

  @Column(name = "selectedBeneficiaryInsOption")
  private BeneficiaryInstitutionOption selectedBeneficiaryInstitutionOption;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "beneficiaryInsOptA_id")
  private PartyInfoForOptionA beneficiaryInstitutionOptionA;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "beneficiaryInsOptD_id")
  private PartyInfoForOptionD beneficiaryInstitutionOptionD;

  public String getRelatedReference() {
    return relatedReference;
  }

  public void setRelatedReference(String relatedReference) {
    this.relatedReference = relatedReference;
  }

  public String getTransactionReferenceNumber() {
    return transactionReferenceNumber;
  }

  public void setTransactionReferenceNumber(String transactionReferenceNumber) {
    this.transactionReferenceNumber = transactionReferenceNumber;
  }

  /*public List<TimeIndication> getTimeIndication() {
  	return timeIndication;
  }

  public void setTimeIndication(List<TimeIndication> timeIndication) {
  	this.timeIndication = timeIndication;
  }*/

  public DateCurrencyAmountInfo getValueDateCurrencyCodeAmount() {
    return valueDateCurrencyCodeAmount;
  }

  public void setValueDateCurrencyCodeAmount(DateCurrencyAmountInfo valueDateCurrencyCodeAmount) {
    this.valueDateCurrencyCodeAmount = valueDateCurrencyCodeAmount;
  }

  public OrderingInstitutionOption getSelectedOrderingInstitutionOption() {
    return selectedOrderingInstitutionOption;
  }

  public void setSelectedOrderingInstitutionOption(
      OrderingInstitutionOption selectedOrderingInstitutionOption) {
    this.selectedOrderingInstitutionOption = selectedOrderingInstitutionOption;
  }

  public PartyInfoForOptionA getOrderingInstitutionOptionA() {
    return orderingInstitutionOptionA;
  }

  public void setOrderingInstitutionOptionA(PartyInfoForOptionA orderingInstitutionOptionA) {
    this.orderingInstitutionOptionA = orderingInstitutionOptionA;
  }

  public PartyInfoForOptionD getOrderingInstitutionOptionD() {
    return orderingInstitutionOptionD;
  }

  public void setOrderingInstitutionOptionD(PartyInfoForOptionD orderingInstitutionOptionD) {
    this.orderingInstitutionOptionD = orderingInstitutionOptionD;
  }

  public SenderReceiverCorrespondentOption getSelectedSenderCorrespondentOption() {
    return selectedSenderCorrespondentOption;
  }

  public void setSelectedSenderCorrespondentOption(
      SenderReceiverCorrespondentOption selectedSenderCorrespondentOption) {
    this.selectedSenderCorrespondentOption = selectedSenderCorrespondentOption;
  }

  public PartyInfoForOptionA getSenderCorrespondentOptionA() {
    return senderCorrespondentOptionA;
  }

  public void setSenderCorrespondentOptionA(PartyInfoForOptionA senderCorrespondentOptionA) {
    this.senderCorrespondentOptionA = senderCorrespondentOptionA;
  }

  public PartyInfoForOptionB getSenderCorrespondentOptionB() {
    return senderCorrespondentOptionB;
  }

  public void setSenderCorrespondentOptionB(PartyInfoForOptionB senderCorrespondentOptionB) {
    this.senderCorrespondentOptionB = senderCorrespondentOptionB;
  }

  public PartyInfoForOptionD getSenderCorrespondentOptionD() {
    return senderCorrespondentOptionD;
  }

  public void setSenderCorrespondentOptionD(PartyInfoForOptionD senderCorrespondentOptionD) {
    this.senderCorrespondentOptionD = senderCorrespondentOptionD;
  }

  public SenderReceiverCorrespondentOption getSelectedReceiversCorrespondentOption() {
    return selectedReceiversCorrespondentOption;
  }

  public void setSelectedReceiversCorrespondentOption(
      SenderReceiverCorrespondentOption selectedReceiversCorrespondentOption) {
    this.selectedReceiversCorrespondentOption = selectedReceiversCorrespondentOption;
  }

  public PartyInfoForOptionA getReceiversCorrespondentOptionA() {
    return receiversCorrespondentOptionA;
  }

  public void setReceiversCorrespondentOptionA(PartyInfoForOptionA receiversCorrespondentOptionA) {
    this.receiversCorrespondentOptionA = receiversCorrespondentOptionA;
  }

  public PartyInfoForOptionB getReceiversCorrespondentOptionB() {
    return receiversCorrespondentOptionB;
  }

  public void setReceiversCorrespondentOptionB(PartyInfoForOptionB receiversCorrespondentOptionB) {
    this.receiversCorrespondentOptionB = receiversCorrespondentOptionB;
  }

  public PartyInfoForOptionD getReceiversCorrespondentOptionD() {
    return receiversCorrespondentOptionD;
  }

  public void setReceiversCorrespondentOptionD(PartyInfoForOptionD receiversCorrespondentOptionD) {
    this.receiversCorrespondentOptionD = receiversCorrespondentOptionD;
  }

  public IntermediaryOption getSelectedIntermediaryOption() {
    return selectedIntermediaryOption;
  }

  public void setSelectedIntermediaryOption(IntermediaryOption selectedIntermediaryOption) {
    this.selectedIntermediaryOption = selectedIntermediaryOption;
  }

  public PartyInfoForOptionA getIntermediaryOptionA() {
    return intermediaryOptionA;
  }

  public void setIntermediaryOptionA(PartyInfoForOptionA intermediaryOptionA) {
    this.intermediaryOptionA = intermediaryOptionA;
  }

  public PartyInfoForOptionD getIntermediaryOptionD() {
    return intermediaryOptionD;
  }

  public void setIntermediaryOptionD(PartyInfoForOptionD intermediaryOptionD) {
    this.intermediaryOptionD = intermediaryOptionD;
  }

  public AccountWithInstitutionOption getSelectedAccountWithInstitutionOption() {
    return selectedAccountWithInstitutionOption;
  }

  public void setSelectedAccountWithInstitutionOption(
      AccountWithInstitutionOption selectedAccountWithInstitutionOption) {
    this.selectedAccountWithInstitutionOption = selectedAccountWithInstitutionOption;
  }

  public PartyInfoForOptionA getAccountWithInstitutionOptionA() {
    return accountWithInstitutionOptionA;
  }

  public void setAccountWithInstitutionOptionA(PartyInfoForOptionA accountWithInstitutionOptionA) {
    this.accountWithInstitutionOptionA = accountWithInstitutionOptionA;
  }

  public PartyInfoForOptionB getAccountWithInstitutionOptionB() {
    return accountWithInstitutionOptionB;
  }

  public void setAccountWithInstitutionOptionB(PartyInfoForOptionB accountWithInstitutionOptionB) {
    this.accountWithInstitutionOptionB = accountWithInstitutionOptionB;
  }

  public PartyInfoForOptionD getAccountWithInstitutionOptionD() {
    return accountWithInstitutionOptionD;
  }

  public void setAccountWithInstitutionOptionD(PartyInfoForOptionD accountWithInstitutionOptionD) {
    this.accountWithInstitutionOptionD = accountWithInstitutionOptionD;
  }

  public BeneficiaryInstitutionOption getSelectedBeneficiaryInstitutionOption() {
    return selectedBeneficiaryInstitutionOption;
  }

  public void setSelectedBeneficiaryInstitutionOption(
      BeneficiaryInstitutionOption selectedBeneficiaryInstitutionOption) {
    this.selectedBeneficiaryInstitutionOption = selectedBeneficiaryInstitutionOption;
  }

  public PartyInfoForOptionA getBeneficiaryInstitutionOptionA() {
    return beneficiaryInstitutionOptionA;
  }

  public void setBeneficiaryInstitutionOptionA(PartyInfoForOptionA beneficiaryInstitutionOptionA) {
    this.beneficiaryInstitutionOptionA = beneficiaryInstitutionOptionA;
  }

  public PartyInfoForOptionD getBeneficiaryInstitutionOptionD() {
    return beneficiaryInstitutionOptionD;
  }

  public void setBeneficiaryInstitutionOptionD(PartyInfoForOptionD beneficiaryInstitutionOptionD) {
    this.beneficiaryInstitutionOptionD = beneficiaryInstitutionOptionD;
  }
  /*public List<SenderToReceiverInformation> getSenderToReceiverInformation() {
  	return senderToReceiverInformation;
  }
  public void setSenderToReceiverInformation(
  		List<SenderToReceiverInformation> senderToReceiverInformation) {
  	this.senderToReceiverInformation = senderToReceiverInformation;
  }*/

}
