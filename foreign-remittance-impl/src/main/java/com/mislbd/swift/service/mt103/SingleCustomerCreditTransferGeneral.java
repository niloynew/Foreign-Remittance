package com.mislbd.swift.service.mt103;

import com.mislbd.swift.service.AbstractMTMessageObject;
import com.mislbd.swift.service.subinfo.PartyInfoForOptionA;
import com.mislbd.swift.service.subinfo.PartyInfoForOptionB;
import com.mislbd.swift.service.subinfo.PartyInfoForOptionD;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "SingleCustCrXferGeneral")
public class SingleCustomerCreditTransferGeneral extends AbstractMTMessageObject {
  /*
   * @Id
   *
   *
   * @GeneratedValue private long id;
   */
  public SingleCustomerCreditTransferGeneral() {
    super();
  }

  @NotNull(message = "Sender reference not found")
  private String sendersReference;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "InstructionCode_Id")
  private InstructionCode instructionCode;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "mt103_id")
  @Fetch(FetchMode.SUBSELECT)
  private List<TimeIndication> timeIndication;

  private BankOperationCodes bankOperationCode;

  private String transactionTypeCode;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "dateCurrencyAmount")
  private DateCurrencyAmountInfo valueDateCurrencyInterbankSettledAmount;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "currInsId")
  private CurrencyAmountInfo currencyinstructedAmount;

  private BigDecimal exchangeRate;

  @Column(name = "selectedOrderingCustOption")
  private OrderingCustomerOption selectedOrderingCustomerOption;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "OrderingCust_ID")
  private AccountIdentifierCodeInfo orderingCustomerOptionA;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "OrderingCustF_ID")
  private OrderingCustomerF orderingCustomerOptionF;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "OrderIngCustK")
  private AccountNameAndAddressInfo orderingCustomerOptionK;

  @Column(name = "selectedOrderingInstOption")
  private OrderingInstitutionOption selectedOrderingInstitutionOption;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "OrderingInsA_ID")
  private PartyInfoForOptionA orderingInstitutionOptionA;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "OrderinInsD_ID")
  private PartyInfoForOptionD orderingInstitutionOptionD;

  @Column(name = "selectedSendersCorrOption")
  private SenderReceiverCorrespondentOption selectedSendersCorrespondentOption;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "senderCorA_ID")
  private PartyInfoForOptionA sendersCorrespondentOptionA;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "senderCorB_ID")
  private PartyInfoForOptionB sendersCorrespondentOptionB;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "senderCorD_ID")
  private PartyInfoForOptionD sendersCorrespondentOptionD;

  @Column(name = "selectedReceiversCorrOption")
  private SenderReceiverCorrespondentOption selectedReceiversCorrespondentOption;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "receiversCorrOptionA")
  private PartyInfoForOptionA receiversCorrespondentOptionA;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "receiversCorrOptionB")
  private PartyInfoForOptionB receiversCorrespondentOptionB;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "receiversCorrOptionD")
  private PartyInfoForOptionD receiversCorrespondentOptionD;

  @Column(name = "selected_third_rim_ins_option")
  private SenderReceiverCorrespondentOption selectedThirdReimbursementInstitutionOption;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "thirdReimInstOptionA")
  private PartyInfoForOptionA thirdReimbursementInstitutionOptionA;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "thirdReimInstOptionB")
  private PartyInfoForOptionB thirdReimbursementInstitutionOptionB;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "thirdReimInstOptionD")
  private PartyInfoForOptionD thirdReimbursementInstitutionOptionD;

  @Column(name = "selectedInterInstOption")
  private IntermediaryOption selectedIntermediaryInstitutionOption;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "intermediaryInstOptionA")
  private PartyInfoForOptionA intermediaryInstitutionOptionA;

  @Column(name = "intermediaryInstOptionC")
  private String intermediaryInstitutionOptionC;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "intermediaryInstOptionD")
  private PartyInfoForOptionD intermediaryInstitutionOptionD;

  @Column(name = "selectedAccWithInstOption")
  private AccountWithInstitutionOption selectedAccountWithInstitutionOption;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "accWithInstOptionA")
  private PartyInfoForOptionA accountWithInstitutionOptionA;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "accWithInstOptionB")
  private PartyInfoForOptionB accountWithInstitutionOptionB;

  @Column(name = "accWithInstOptionC")
  private String accountWithInstitutionOptionC;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "accWithInstOptionD")
  private PartyInfoForOptionD accountWithInstitutionOptionD;

  @Column(name = "selectedBenefCustOption")
  private BeneficiaryCustomerOption selectedBeneficiaryCustomerOption;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "benefCustOptionNoLetter")
  private AccountNameAndAddressInfo beneficiaryCustomerOptionNoLetter;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "benefCustOptionA")
  private AccountIdentifierCodeInfo beneficiaryCustomerOptionA;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "benefCustOptionF")
  private AccNoNameAddress beneficiaryCustomerOptionF;

  private String remittanceInformation;
  private String detailsOfCharges;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "senderChargeId")
  private CurrencyAmountInfo sendersCharges;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "RecevrsChrgs_ID")
  private CurrencyAmountInfo receiversCharges;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "MT103_ID")
  @Fetch(FetchMode.SUBSELECT)
  private List<SenderToReceiverInformation> senderToReceiverInformation;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private CodeCntryCodeNar regulatoryReporting;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "SendingInsID")
  private PartyInfoForOptionA sendingInstitution;

  private String envelopeContents;

  public String getSendersReference() {
    return sendersReference;
  }

  public void setSendersReference(String sendersReference) {
    this.sendersReference = sendersReference;
  }

  public List<TimeIndication> getTimeIndication() {
    return timeIndication;
  }

  public void setTimeIndication(List<TimeIndication> timeIndication) {
    this.timeIndication = timeIndication;
  }

  public BankOperationCodes getBankOperationCode() {
    return bankOperationCode;
  }

  public void setBankOperationCode(BankOperationCodes bankOperationCode) {
    this.bankOperationCode = bankOperationCode;
  }

  public InstructionCode getInstructionCode() {
    return instructionCode;
  }

  public void setInstructionCode(InstructionCode instructionCode) {
    this.instructionCode = instructionCode;
  }

  public String getTransactionTypeCode() {
    return transactionTypeCode;
  }

  public void setTransactionTypeCode(String transactionTypeCode) {
    this.transactionTypeCode = transactionTypeCode;
  }

  public DateCurrencyAmountInfo getValueDateCurrencyInterbankSettledAmount() {
    return valueDateCurrencyInterbankSettledAmount;
  }

  public void setValueDateCurrencyInterbankSettledAmount(
      DateCurrencyAmountInfo valueDateCurrencyInterbankSettledAmount) {
    this.valueDateCurrencyInterbankSettledAmount = valueDateCurrencyInterbankSettledAmount;
  }

  public CurrencyAmountInfo getCurrencyinstructedAmount() {
    return currencyinstructedAmount;
  }

  public void setCurrencyinstructedAmount(CurrencyAmountInfo currencyinstructedAmount) {
    this.currencyinstructedAmount = currencyinstructedAmount;
  }

  public BigDecimal getExchangeRate() {
    return exchangeRate;
  }

  public void setExchangeRate(BigDecimal exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  public OrderingCustomerOption getSelectedOrderingCustomerOption() {
    return selectedOrderingCustomerOption;
  }

  public void setSelectedOrderingCustomerOption(
      OrderingCustomerOption selectedOrderingCustomerOption) {
    this.selectedOrderingCustomerOption = selectedOrderingCustomerOption;
  }

  public AccountIdentifierCodeInfo getOrderingCustomerOptionA() {
    return orderingCustomerOptionA;
  }

  public void setOrderingCustomerOptionA(AccountIdentifierCodeInfo orderingCustomerOptionA) {
    this.orderingCustomerOptionA = orderingCustomerOptionA;
  }

  public OrderingCustomerF getOrderingCustomerOptionF() {
    return orderingCustomerOptionF;
  }

  public void setOrderingCustomerOptionF(OrderingCustomerF orderingCustomerOptionF) {
    this.orderingCustomerOptionF = orderingCustomerOptionF;
  }

  public AccountNameAndAddressInfo getOrderingCustomerOptionK() {
    return orderingCustomerOptionK;
  }

  public void setOrderingCustomerOptionK(AccountNameAndAddressInfo orderingCustomerOptionK) {
    this.orderingCustomerOptionK = orderingCustomerOptionK;
  }

  public PartyInfoForOptionA getOrderingInstitutionOptionA() {
    return orderingInstitutionOptionA;
  }

  public void setOrderingInstitutionOptionA(PartyInfoForOptionA orderingInstitutionOptionA) {
    this.orderingInstitutionOptionA = orderingInstitutionOptionA;
  }

  public SenderReceiverCorrespondentOption getSelectedSendersCorrespondentOption() {
    return selectedSendersCorrespondentOption;
  }

  public void setSelectedSendersCorrespondentOption(
      SenderReceiverCorrespondentOption selectedSendersCorrespondentOption) {
    this.selectedSendersCorrespondentOption = selectedSendersCorrespondentOption;
  }

  public PartyInfoForOptionA getSendersCorrespondentOptionA() {
    return sendersCorrespondentOptionA;
  }

  public void setSendersCorrespondentOptionA(PartyInfoForOptionA sendersCorrespondentOptionA) {
    this.sendersCorrespondentOptionA = sendersCorrespondentOptionA;
  }

  public PartyInfoForOptionB getSendersCorrespondentOptionB() {
    return sendersCorrespondentOptionB;
  }

  public void setSendersCorrespondentOptionB(PartyInfoForOptionB sendersCorrespondentOptionB) {
    this.sendersCorrespondentOptionB = sendersCorrespondentOptionB;
  }

  public PartyInfoForOptionA getReceiversCorrespondentOptionA() {
    return receiversCorrespondentOptionA;
  }

  public void setReceiversCorrespondentOptionA(PartyInfoForOptionA receiversCorrespondentOptionA) {
    this.receiversCorrespondentOptionA = receiversCorrespondentOptionA;
  }

  public PartyInfoForOptionA getThirdReimbursementInstitutionOptionA() {
    return thirdReimbursementInstitutionOptionA;
  }

  public void setThirdReimbursementInstitutionOptionA(
      PartyInfoForOptionA thirdReimbursementInstitutionOptionA) {
    this.thirdReimbursementInstitutionOptionA = thirdReimbursementInstitutionOptionA;
  }

  public PartyInfoForOptionA getIntermediaryInstitutionOptionA() {
    return intermediaryInstitutionOptionA;
  }

  public void setIntermediaryInstitutionOptionA(
      PartyInfoForOptionA intermediaryInstitutionOptionA) {
    this.intermediaryInstitutionOptionA = intermediaryInstitutionOptionA;
  }

  public PartyInfoForOptionA getAccountWithInstitutionOptionA() {
    return accountWithInstitutionOptionA;
  }

  public void setAccountWithInstitutionOptionA(PartyInfoForOptionA accountWithInstitutionOptionA) {
    this.accountWithInstitutionOptionA = accountWithInstitutionOptionA;
  }

  public BeneficiaryCustomerOption getSelectedBeneficiaryCustomerOption() {
    return selectedBeneficiaryCustomerOption;
  }

  public void setSelectedBeneficiaryCustomerOption(
      BeneficiaryCustomerOption selectedBeneficiaryCustomerOption) {
    this.selectedBeneficiaryCustomerOption = selectedBeneficiaryCustomerOption;
  }

  public AccountNameAndAddressInfo getBeneficiaryCustomerOptionNoLetter() {
    return beneficiaryCustomerOptionNoLetter;
  }

  public void setBeneficiaryCustomerOptionNoLetter(
      AccountNameAndAddressInfo beneficiaryCustomerOptionNoLetter) {
    this.beneficiaryCustomerOptionNoLetter = beneficiaryCustomerOptionNoLetter;
  }

  public AccountIdentifierCodeInfo getBeneficiaryCustomerOptionA() {
    return beneficiaryCustomerOptionA;
  }

  public void setBeneficiaryCustomerOptionA(AccountIdentifierCodeInfo beneficiaryCustomerOptionA) {
    this.beneficiaryCustomerOptionA = beneficiaryCustomerOptionA;
  }

  public String getRemittanceInformation() {
    return remittanceInformation;
  }

  /* public void setRemittanceInformation(String remittanceInformation) {
    this.remittanceInformation =
        SwiftStringUtiliy.getTextFromMultilineControls(remittanceInformation);
  }*/

  public String getDetailsOfCharges() {
    return detailsOfCharges;
  }

  public void setDetailsOfCharges(String detailsOfCharges) {
    this.detailsOfCharges = detailsOfCharges;
  }

  public CurrencyAmountInfo getSendersCharges() {
    return sendersCharges;
  }

  public void setSendersCharges(CurrencyAmountInfo sendersCharges) {
    this.sendersCharges = sendersCharges;
  }

  public CurrencyAmountInfo getReceiversCharges() {
    return receiversCharges;
  }

  public void setReceiversCharges(CurrencyAmountInfo receiversCharges) {
    this.receiversCharges = receiversCharges;
  }

  public List<SenderToReceiverInformation> getSenderToReceiverInformation() {
    return senderToReceiverInformation;
  }

  public void setSenderToReceiverInformation(
      List<SenderToReceiverInformation> senderToReceiverInformation) {
    this.senderToReceiverInformation = senderToReceiverInformation;
  }

  public String getIntermediaryInstitutionOptionC() {
    return intermediaryInstitutionOptionC;
  }

  public void setIntermediaryInstitutionOptionC(String intermediaryInstitutionOptionC) {
    this.intermediaryInstitutionOptionC = intermediaryInstitutionOptionC;
  }

  public PartyInfoForOptionD getIntermediaryInstitutionOptionD() {
    return intermediaryInstitutionOptionD;
  }

  public void setIntermediaryInstitutionOptionD(
      PartyInfoForOptionD intermediaryInstitutionOptionD) {
    this.intermediaryInstitutionOptionD = intermediaryInstitutionOptionD;
  }

  public AccountWithInstitutionOption getSelectedAccountWithInstitutionOption() {
    return selectedAccountWithInstitutionOption;
  }

  public void setSelectedAccountWithInstitutionOption(
      AccountWithInstitutionOption selectedAccountWithInstitutionOption) {
    this.selectedAccountWithInstitutionOption = selectedAccountWithInstitutionOption;
  }

  public PartyInfoForOptionA getSendingInstitution() {
    return sendingInstitution;
  }

  public void setSendingInstitution(PartyInfoForOptionA sendingInstitution) {
    this.sendingInstitution = sendingInstitution;
  }

  public OrderingInstitutionOption getSelectedOrderingInstitutionOption() {
    return selectedOrderingInstitutionOption;
  }

  public void setSelectedOrderingInstitutionOption(
      OrderingInstitutionOption selectedOrderingInstitutionOption) {
    this.selectedOrderingInstitutionOption = selectedOrderingInstitutionOption;
  }

  public PartyInfoForOptionD getOrderingInstitutionOptionD() {
    return orderingInstitutionOptionD;
  }

  public void setOrderingInstitutionOptionD(PartyInfoForOptionD orderingInstitutionOptionD) {
    this.orderingInstitutionOptionD = orderingInstitutionOptionD;
  }

  public PartyInfoForOptionD getSendersCorrespondentOptionD() {
    return sendersCorrespondentOptionD;
  }

  public void setSendersCorrespondentOptionD(PartyInfoForOptionD sendersCorrespondentOptionD) {
    this.sendersCorrespondentOptionD = sendersCorrespondentOptionD;
  }

  public SenderReceiverCorrespondentOption getSelectedReceiversCorrespondentOption() {
    return selectedReceiversCorrespondentOption;
  }

  public void setSelectedReceiversCorrespondentOption(
      SenderReceiverCorrespondentOption selectedReceiversCorrespondentOption) {
    this.selectedReceiversCorrespondentOption = selectedReceiversCorrespondentOption;
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

  public SenderReceiverCorrespondentOption getSelectedThirdReimbursementInstitutionOption() {
    return selectedThirdReimbursementInstitutionOption;
  }

  public void setSelectedThirdReimbursementInstitutionOption(
      SenderReceiverCorrespondentOption selectedThirdReimbursementInstitutionOption) {
    this.selectedThirdReimbursementInstitutionOption = selectedThirdReimbursementInstitutionOption;
  }

  public PartyInfoForOptionB getThirdReimbursementInstitutionOptionB() {
    return thirdReimbursementInstitutionOptionB;
  }

  public void setThirdReimbursementInstitutionOptionB(
      PartyInfoForOptionB thirdReimbursementInstitutionOptionB) {
    this.thirdReimbursementInstitutionOptionB = thirdReimbursementInstitutionOptionB;
  }

  public PartyInfoForOptionD getThirdReimbursementInstitutionOptionD() {
    return thirdReimbursementInstitutionOptionD;
  }

  public void setThirdReimbursementInstitutionOptionD(
      PartyInfoForOptionD thirdReimbursementInstitutionOptionD) {
    this.thirdReimbursementInstitutionOptionD = thirdReimbursementInstitutionOptionD;
  }

  public IntermediaryOption getSelectedIntermediaryInstitutionOption() {
    return selectedIntermediaryInstitutionOption;
  }

  public void setSelectedIntermediaryInstitutionOption(
      IntermediaryOption selectedIntermediaryInstitutionOption) {
    this.selectedIntermediaryInstitutionOption = selectedIntermediaryInstitutionOption;
  }

  public PartyInfoForOptionB getAccountWithInstitutionOptionB() {
    return accountWithInstitutionOptionB;
  }

  public void setAccountWithInstitutionOptionB(PartyInfoForOptionB accountWithInstitutionOptionB) {
    this.accountWithInstitutionOptionB = accountWithInstitutionOptionB;
  }

  public String getAccountWithInstitutionOptionC() {
    return accountWithInstitutionOptionC;
  }

  public void setAccountWithInstitutionOptionC(String accountWithInstitutionOptionC) {
    this.accountWithInstitutionOptionC = accountWithInstitutionOptionC;
  }

  public PartyInfoForOptionD getAccountWithInstitutionOptionD() {
    return accountWithInstitutionOptionD;
  }

  public void setAccountWithInstitutionOptionD(PartyInfoForOptionD accountWithInstitutionOptionD) {
    this.accountWithInstitutionOptionD = accountWithInstitutionOptionD;
  }

  public String getEnvelopeContents() {
    return envelopeContents;
  }

  public void setEnvelopeContents(String envelopeContents) {
    this.envelopeContents = envelopeContents;
  }

  public AccNoNameAddress getBeneficiaryCustomerOptionF() {
    return beneficiaryCustomerOptionF;
  }

  public void setBeneficiaryCustomerOptionF(AccNoNameAddress beneficiaryCustomerOptionF) {
    this.beneficiaryCustomerOptionF = beneficiaryCustomerOptionF;
  }

  public CodeCntryCodeNar getRegulatoryReporting() {
    return regulatoryReporting;
  }

  public void setRegulatoryReporting(CodeCntryCodeNar regulatoryReporting) {
    this.regulatoryReporting = regulatoryReporting;
  }

  /*
   * public long getId() { return id; }
   *
   * public void setId(long id) { this.id = id; }
   */
  @Override
  public String getType() {
    return "103";
  }

  @Override
  public void nullifiyChildren() {
    if (this.getInstructionCode() != null) {
      this.getInstructionCode().setId(0);
    }

    if (this.getTimeIndication() != null && this.getTimeIndication().size() > 0) {
      for (TimeIndication t : this.getTimeIndication()) {
        t.setId(0);
      }
    }

    if (this.getValueDateCurrencyInterbankSettledAmount() != null) {
      this.getValueDateCurrencyInterbankSettledAmount().setId(0);
    }

    if (this.getCurrencyinstructedAmount() != null) {
      this.getCurrencyinstructedAmount().setId(0);
    }

    if (this.getSelectedOrderingCustomerOption() != null) {
      switch (this.getSelectedOrderingCustomerOption()) {
        case OptionA:
          if (this.getOrderingCustomerOptionA() != null) {
            this.getOrderingCustomerOptionA().setId(0);
          }
          break;
        case OptionF:
          if (this.getOrderingCustomerOptionF() != null) {
            if (this.getOrderingCustomerOptionF().getSelectedOrderingCustomerFOption() != null) {
              switch (this.getOrderingCustomerOptionF().getSelectedOrderingCustomerFOption()) {
                case Option_1:
                  this.getOrderingCustomerOptionF().getOrderingCustomerF_1().setId(0);

                  break;
                case Option_2:
                  this.getOrderingCustomerOptionF().getOrderingCustomerF_2().setId(0);
                  break;

                default:
                  break;
              }
            }

            this.getOrderingCustomerOptionF().setId(0);
          }
          break;
        case OptionK:
          if (this.getOrderingCustomerOptionK() != null) {
            this.getOrderingCustomerOptionK().setId(0);
          }
          break;
      }
    }

    if (this.getSelectedOrderingInstitutionOption() != null) {
      switch (this.getSelectedOrderingInstitutionOption()) {
        case OptionA:
          if (this.getOrderingInstitutionOptionA() != null)
            this.getOrderingInstitutionOptionA().setId(0);
          break;
        case OptionD:
          if (this.getOrderingInstitutionOptionD() != null)
            this.getOrderingInstitutionOptionD().setId(0);
          break;
      }
    }

    if (this.getSelectedSendersCorrespondentOption() != null) {
      switch (this.getSelectedSendersCorrespondentOption()) {
        case OptionA:
          if (this.getSendersCorrespondentOptionA() != null)
            this.getSendersCorrespondentOptionA().setId(0);
          break;
        case OptionB:
          if (this.getSendersCorrespondentOptionB() != null)
            this.getSendersCorrespondentOptionB().setId(0);
          break;
        case OptionD:
          if (this.getSendersCorrespondentOptionD() != null)
            this.getSendersCorrespondentOptionD().setId(0);
          break;
      }
    }

    if (this.getSelectedReceiversCorrespondentOption() != null) {
      switch (this.getSelectedReceiversCorrespondentOption()) {
        case OptionA:
          if (this.getReceiversCorrespondentOptionA() != null)
            this.getReceiversCorrespondentOptionA().setId(0);
          break;
        case OptionB:
          if (this.getReceiversCorrespondentOptionB() != null)
            this.getReceiversCorrespondentOptionB().setId(0);
          break;
        case OptionD:
          if (this.getReceiversCorrespondentOptionD() != null)
            this.getReceiversCorrespondentOptionD().setId(0);
          break;
      }
    }

    if (this.getSelectedThirdReimbursementInstitutionOption() != null) {
      switch (this.getSelectedThirdReimbursementInstitutionOption()) {
        case OptionA:
          if (this.getThirdReimbursementInstitutionOptionA() != null)
            this.getThirdReimbursementInstitutionOptionA().setId(0);
          break;
        case OptionB:
          if (this.getThirdReimbursementInstitutionOptionB() != null)
            this.getThirdReimbursementInstitutionOptionB().setId(0);
          break;
        case OptionD:
          if (this.getThirdReimbursementInstitutionOptionD() != null)
            this.getThirdReimbursementInstitutionOptionD().setId(0);
          break;
      }
    }

    if (this.getSelectedIntermediaryInstitutionOption() != null) {
      switch (this.getSelectedIntermediaryInstitutionOption()) {
        case OptionA:
          if (this.getIntermediaryInstitutionOptionA() != null)
            this.getIntermediaryInstitutionOptionA().setId(0);
          break;
        case OptionD:
          if (this.getIntermediaryInstitutionOptionD() != null)
            this.getIntermediaryInstitutionOptionD().setId(0);
          break;
        case OptionC:
          break;
        default:
          break;
      }
    }

    if (this.getSelectedAccountWithInstitutionOption() != null) {
      switch (this.getSelectedAccountWithInstitutionOption()) {
        case OptionA:
          if (this.getAccountWithInstitutionOptionA() != null)
            this.getAccountWithInstitutionOptionA().setId(0);
          break;
        case OptionB:
          if (this.getAccountWithInstitutionOptionB() != null)
            this.getAccountWithInstitutionOptionB().setId(0);
          break;
        case OptionD:
          if (this.getAccountWithInstitutionOptionD() != null)
            this.getAccountWithInstitutionOptionD().setId(0);
          break;
        case OptionC:
          break;
        default:
          break;
      }
    }

    if (this.getSelectedBeneficiaryCustomerOption() != null) {
      switch (this.getSelectedBeneficiaryCustomerOption()) {
        case NoLetterOption:
          if (this.getBeneficiaryCustomerOptionNoLetter() != null)
            this.getBeneficiaryCustomerOptionNoLetter().setId(0);
          break;
        case OptionA:
          if (this.getBeneficiaryCustomerOptionA() != null)
            this.getBeneficiaryCustomerOptionA().setId(0);
          break;
        case OptionF:
          if (this.getBeneficiaryCustomerOptionF() != null)
            this.getBeneficiaryCustomerOptionF().setId(0);
          break;
      }
    }

    if (this.getSendersCharges() != null) this.getSendersCharges().setId(0);
    if (this.getReceiversCharges() != null) this.getReceiversCharges().setId(0);
    if (this.getRegulatoryReporting() != null) this.getRegulatoryReporting().setId(0);
    if (this.getSendingInstitution() != null) this.getSendingInstitution().setId(0);

    if (this.getSenderToReceiverInformation() != null
        && this.getSenderToReceiverInformation().size() > 0) {
      for (SenderToReceiverInformation s : this.getSenderToReceiverInformation()) {
        s.setId(0);
      }
    }
  }
}
