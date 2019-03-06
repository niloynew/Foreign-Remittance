package com.mislbd.swift.service.mt202;

import java.util.List;
import javax.persistence.*;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "FUNDTRANSFER_FI")
public class GeneralFinancialInstitutionTransfer extends GeneralFinancialInsCommon
    implements Cloneable {

  public GeneralFinancialInstitutionTransfer() {
    super();
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @Fetch(FetchMode.SUBSELECT)
  @JoinColumn(name = "MT202Gen_ID")
  private List<SenderToReceiverInformation> senderToReceiverInformation;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Fetch(FetchMode.SUBSELECT)
  @JoinColumn(name = "mt202_id")
  private List<TimeIndication> timeIndication;

  public List<TimeIndication> getTimeIndication() {
    return timeIndication;
  }

  public void setTimeIndication(List<TimeIndication> timeIndication) {
    this.timeIndication = timeIndication;
  }

  public List<SenderToReceiverInformation> getSenderToReceiverInformation() {
    return senderToReceiverInformation;
  }

  public void setSenderToReceiverInformation(
      List<SenderToReceiverInformation> senderToReceiverInformation) {
    this.senderToReceiverInformation = senderToReceiverInformation;
  }

  @Override
  @JsonIgnore
  public String getType() {
    return "202";
  }

  @Override
  public void nullifiyChildren() {
    if (this.getTimeIndication() != null && this.getTimeIndication().size() > 0) {
      for (TimeIndication t : this.getTimeIndication()) {
        t.setId(0);
      }
    }

    if (this.getSelectedSenderCorrespondentOption() != null) {
      switch (this.getSelectedSenderCorrespondentOption()) {
        case OptionA:
          if (this.getSenderCorrespondentOptionA() != null) {
            this.getSenderCorrespondentOptionA().setId(0);
          }
          break;
        case OptionB:
          if (this.getSenderCorrespondentOptionB() != null) {
            this.getSenderCorrespondentOptionB().setId(0);
          }
          break;
        case OptionD:
          if (this.getSenderCorrespondentOptionD() != null) {
            this.getSenderCorrespondentOptionD().setId(0);
          }
          break;
      }
    }
    if (this.getSelectedReceiversCorrespondentOption() != null) {
      switch (this.getSelectedReceiversCorrespondentOption()) {
        case OptionA:
          if (this.getReceiversCorrespondentOptionA() != null) {
            this.getReceiversCorrespondentOptionA().setId(0);
          }
          break;
        case OptionB:
          if (this.getReceiversCorrespondentOptionB() != null) {
            this.getReceiversCorrespondentOptionB().setId(0);
          }
          break;
        case OptionD:
          if (this.getReceiversCorrespondentOptionD() != null) {
            this.getReceiversCorrespondentOptionD().setId(0);
          }
          break;
      }
    }
    if (this.getSelectedIntermediaryOption() != null) {
      switch (this.getSelectedIntermediaryOption()) {
        case OptionA:
          if (this.getIntermediaryOptionA() != null) {
            this.getIntermediaryOptionA().setId(0);
          }
          break;

        case OptionD:
          if (this.getIntermediaryOptionD() != null) {
            this.getIntermediaryOptionD().setId(0);
          }
          break;
      }
    }
    if (this.getSelectedBeneficiaryInstitutionOption() != null) {
      switch (this.getSelectedBeneficiaryInstitutionOption()) {
        case OptionA:
          if (this.getBeneficiaryInstitutionOptionA() != null) {
            this.getBeneficiaryInstitutionOptionA().setId(0);
          }
          break;

        case OptionD:
          if (this.getBeneficiaryInstitutionOptionD() != null) {
            this.getBeneficiaryInstitutionOptionD().setId(0);
          }
          break;
      }
    }
    if (this.getSelectedAccountWithInstitutionOption() != null) {
      switch (this.getSelectedAccountWithInstitutionOption()) {
        case OptionA:
          if (this.getAccountWithInstitutionOptionA() != null) {
            this.getAccountWithInstitutionOptionA().setId(0);
          }
          break;
        case OptionB:
          if (this.getAccountWithInstitutionOptionB() != null) {
            this.getAccountWithInstitutionOptionB().setId(0);
          }
          break;
        case OptionD:
          if (this.getAccountWithInstitutionOptionD() != null) {
            this.getAccountWithInstitutionOptionD().setId(0);
          }
          break;
      }
    }
    if (this.getValueDateCurrencyCodeAmount() != null) {
      this.getValueDateCurrencyCodeAmount().setId(0);
    }
    if (this.getSelectedOrderingInstitutionOption() != null) {
      switch (this.getSelectedOrderingInstitutionOption()) {
        case OptionA:
          if (this.getOrderingInstitutionOptionA() != null) {
            this.getOrderingInstitutionOptionA().setId(0);
          }
          break;

        case OptionD:
          if (this.getOrderingInstitutionOptionD() != null) {
            this.getOrderingInstitutionOptionD().setId(0);
          }
          break;
      }
    }
    if (this.getSenderToReceiverInformation() != null
        && this.getSenderToReceiverInformation().size() > 0) {
      for (SenderToReceiverInformation s : this.getSenderToReceiverInformation()) {
        if (s != null) {
          s.setId(0);
        }
      }
    }
  }

  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
