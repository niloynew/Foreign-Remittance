package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.service.LocaleMessages;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public enum Error {
  CHARGE_MAPPING_NOT_FOUND("232", "0001") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message = localeMessages.get(this.getModule() + this.getCode()).orElse("IDProduct not found");
      return message;
    }
  },

  CHARGE_AMOUNT_NOT_FOUND("230", "0004") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages.get(this.getModule() + this.getCode()).orElse("Charge amount not found.");
      return message;
    }
  },

  CHARGE_CONFIGURATION_CONFLICT_FOUND("230", "0002") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages
              .get(this.getModule() + this.getCode())
              .orElse("Charge configuration exists");
      return message;
    }
  },

  CHARGE_SLAB_NOT_FOUND("230", "0003") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages.get(this.getModule() + this.getCode()).orElse("Charge slab not found.");
      return message;
    }
  },

  CHARGE_PERCENTAGE_NOT_FOUND("230", "0005") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages
              .get(this.getModule() + this.getCode())
              .orElse("Charge percentage not found.");
      return message;
    }
  },

  CHARGE_VAT_AMOUNT_NOT_FOUND("230", "0006") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages.get(this.getModule() + this.getCode()).orElse("VAT amount not found.");
      return message;
    }
  },

  CHARGE_VAT_PERCENTAGE_NOT_FOUND("230", "0007") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages.get(this.getModule() + this.getCode()).orElse("VAT percentage not found.");
      return message;
    }
  },

  CHARGE_MIN_MAX_EXCEPTION("230", "0008") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message = localeMessages.get(this.getModule() + this.getCode()).orElse("");
      return message;
    }
  },

  // ID product region
  ID_PRODUCT_NOT_FOUND("260", "0001") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message = localeMessages.get(this.getModule() + this.getCode()).orElse("IDProduct not found");
      return message;
    }
  },

  ID_PRODUCT_CURRENCIES_NOT_EXISTS("260", "0003") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages.get(this.getModule() + this.getCode()).orElse("Currencies is required");
      return message;
    }
  },

  // Shadow account region
  ACCOUNT_NOT_FOUND("260", "0004") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message = localeMessages.get(this.getModule() + this.getCode()).orElse("Account not found");
      return message;
    }
  },
  GENERATED_SHADOW_ACCOUNT_NUMBER_NOT_FOUND("260", "002") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages
              .get(this.getModule() + this.getCode())
              .orElse("Generated shadow account number not found.");
      return message;
    }
  },

  // transaction region
  REMITTANCE_TRANSACTION_NOT_FOUND_EXCEPTION("260", "0010") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages
              .get(this.getModule() + this.getCode())
              .orElse("Remittance transaction not found");
      return message;
    }
  },
  REMITTANCE_TRANSACTION_EXCEPTION("230", "0009") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages
              .get(this.getModule() + this.getCode())
              .orElse("Remittance transaction exception");
      return message;
    }
  },
  SWIFT_REGISTER_NOT_FOUND_EXCEPTION("230", "0011") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages.get(this.getModule() + this.getCode()).orElse("Swift Register not found");
      return message;
    }
  },
  ;

  // region <R>
  Error(final String module, final String code) {
    this.code = code;
    this.module = module;
  }

  private String code;
  private String module;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getModule() {
    return module;
  }

  public void setModule(String module) {
    this.module = module;
  }

  // Lookup table
  private static final Map<String, Error> lookup = new HashMap<>();

  // Populate the lookup table on loading time
  static {
    EnumSet.allOf(Error.class).forEach(r -> lookup.put(r.getCode(), r));
  }

  // Reverse lookup
  public static Error get(String code) {
    return lookup.get(code);
  }

  public abstract String getMessages();

  public abstract String getMessages(LocaleMessages localeMessages);

  @Component
  public static class ForeignRemittanceLocaleMessagesInjector {

    @Autowired private final LocaleMessages localeMessages;

    public ForeignRemittanceLocaleMessagesInjector(LocaleMessages localeMessages) {
      this.localeMessages = localeMessages;
      for (Error error : EnumSet.allOf(Error.class)) error.getMessages(localeMessages);
    }
  }
  // endregion

}
