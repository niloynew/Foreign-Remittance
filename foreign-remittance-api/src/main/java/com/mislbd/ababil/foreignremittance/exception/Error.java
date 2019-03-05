package com.mislbd.ababil.foreignremittance.exception;

import com.mislbd.ababil.asset.service.LocaleMessages;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public enum Error {
  ID_PRODUCT_NOT_FOUND("231", "0001") {
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
  };

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
