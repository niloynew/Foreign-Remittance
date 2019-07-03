package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.AccountStatement;
import com.mislbd.ababil.foreignremittance.repository.schema.AccountStatementEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountStatementMapper {
  public ResultMapper<AccountStatementEntity, AccountStatement> entityToDomain() {
    return entity ->
        new AccountStatement()
            .setId(entity.getId())
            .setAccountId(entity.getAccountId())
            .setDebit(entity.getDebit())
            .setCredit(entity.getCredit())
            .setDebitLcy(entity.getDebitLcy())
            .setCreditLcy(entity.getCreditLcy())
            .setExchangeRate(entity.getExchangeRate())
            .setTxnDefinitionId(entity.getTxnDefinitionId())
            .setTxnNarration(entity.getTxnNarration())
            .setTxnDate(entity.getTxnDate())
            .setGlDetailId(entity.getGlDetailId())
            .setValid(entity.getValid())
            .setGlobalTxnNo(entity.getGlobalTxnNo())
            .setOwnerBranchId(entity.getOwnerBranchId())
            .setInitiatorBranchId(entity.getInitiatorBranchId())
            .setEventId(entity.getEventId())
            .setBatchNumber(entity.getBatchNumber())
            .setValueDate(entity.getValueDate());
  }
}
