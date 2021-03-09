package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.ShadowTransactionRecord;
import com.mislbd.ababil.foreignremittance.repository.jpa.ShadowAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.ShadowTransactionRecordEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class ShadowTransactionRecordMapper {

  private final ShadowAccountRepository shadowAccountRepository;

  public ShadowTransactionRecordMapper(ShadowAccountRepository shadowAccountRepository) {
    this.shadowAccountRepository = shadowAccountRepository;
  }

  public ResultMapper<ShadowTransactionRecordEntity, ShadowTransactionRecord> entityToDomain() {
    return entity ->
        new ShadowTransactionRecord()
            .setId(entity.getId())
            .setAccountId(entity.getAccountId())
            .setAccountNumber(shadowAccountRepository.findById(entity.getId()).get().getNumber())
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
            .setValueDate(entity.getValueDate())
            .setPostBalance(entity.getPostBalance())
            .setReconcileStatus(entity.getReconcileStatus());
  }
}
