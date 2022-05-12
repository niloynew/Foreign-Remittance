package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.ReconcileTxnLog;
import com.mislbd.ababil.foreignremittance.repository.jpa.ReconcileTxnLogRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.ReconcileTxnLogEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class ReconcileTxnLogMapper {
  private final ReconcileTxnLogRepository reconcileTxnLogRepository;

  public ReconcileTxnLogMapper(ReconcileTxnLogRepository reconcileTxnLogRepository) {
    this.reconcileTxnLogRepository = reconcileTxnLogRepository;
  }

  public ResultMapper<ReconcileTxnLogEntity, ReconcileTxnLog> entityToDomain() {
    return entity ->
        new ReconcileTxnLog()
            .setId(entity.getId())
            .setAccountNumber(entity.getAccountNumber())
            .setExecutedBy(entity.getExecutedBy())
            .setGlobalTxnNo(entity.getGlobalTxnNo())
            .setInitiatingTime(entity.getInitiatingTime())
            .setInitiator(entity.getInitiator())
            .setInitiatorBranch(entity.getInitiatorBranch())
            .setTxnDate(entity.getTxnDate())
            .setTxnNarration(entity.getTxnNarration());
  }

  public ResultMapper<ReconcileTxnLog, ReconcileTxnLogEntity> domainToEntity() {
    return domain ->
        reconcileTxnLogRepository
            .findByGlobalTxnNo(domain.getGlobalTxnNo())
            .orElseGet(ReconcileTxnLogEntity::new)
            .setId(domain.getId())
            .setAccountNumber(domain.getAccountNumber())
            .setExecutedBy(domain.getExecutedBy())
            .setGlobalTxnNo(domain.getGlobalTxnNo())
            .setInitiatingTime(domain.getInitiatingTime())
            .setInitiator(domain.getInitiator())
            .setInitiatorBranch(domain.getInitiatorBranch())
            .setTxnDate(domain.getTxnDate())
            .setTxnNarration(domain.getTxnNarration());
  }
}
