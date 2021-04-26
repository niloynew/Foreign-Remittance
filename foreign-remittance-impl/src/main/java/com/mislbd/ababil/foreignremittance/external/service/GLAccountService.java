package com.mislbd.ababil.foreignremittance.external.service;

import com.mislbd.ababil.foreignremittance.exception.ForeignRemittanceBaseException;
import com.mislbd.ababil.foreignremittance.external.domain.GLAccount;
import com.mislbd.ababil.foreignremittance.external.repository.GeneralLedgerClient;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GLAccountService {
  private final GeneralLedgerClient generalLedgerClient;

  public GLAccountService(GeneralLedgerClient generalLedgerClient) {
    this.generalLedgerClient = generalLedgerClient;
  }

  public GLAccount getGLAccountByCode(String glCode) {
    try {
      Optional<GLAccount> account = generalLedgerClient.getGeneralLedgerAccount(glCode);
      if (account.isPresent()) {
        return account.get();
      } else throw new ForeignRemittanceBaseException("GL not found");
    } catch (Exception e) {
      throw new ForeignRemittanceBaseException("GL not found");
    }
  }
}
