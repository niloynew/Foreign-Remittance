package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.NostroTransactionRecord;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroTransactionRecordRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.NostroTransactionRecordEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class NostroAccountRecordMapper {

  private final NostroTransactionRecordRepository repository;

  public NostroAccountRecordMapper(NostroTransactionRecordRepository repository) {
    this.repository = repository;
  }

  public ResultMapper<NostroTransactionRecordEntity, NostroTransactionRecord> entityToDomain() {
    return entity ->
        new NostroTransactionRecord()
            .setId(entity.getId())
            .setNostroCreditAmtCcy(entity.getNostroCreditAmtCcy())
            .setNostroDebitAmtCcy(entity.getNostroDebitAmtCcy())
            .setNostroCurCode(entity.getNostroCurCode())
            .setNostroDrCr(entity.getNostroDrCr())
            .setNostroValueDate(entity.getNostroValueDate())
            .setNostroRefAccno(entity.getNostroRefAccountNo())
            .setNostroTrRefNo(entity.getNostroTxnReferenceNo())
            .setNostroTrpostbalance(entity.getNostroTxnPostBalance());
  }

  public ResultMapper<NostroTransactionRecord, NostroTransactionRecordEntity> domainToEntity() {
    return domain ->
        repository
            .findById(domain.getId())
            .orElseGet(NostroTransactionRecordEntity::new)
            .setNostroCreditAmtCcy(domain.getNostroCreditAmtCcy())
            .setNostroDebitAmtCcy(domain.getNostroDebitAmtCcy())
            .setNostroCurCode(domain.getNostroCurCode())
            .setNostroDrCr(domain.getNostroDrCr())
            .setNostroValueDate(domain.getNostroValueDate())
            .setNostroRefAccountNo(domain.getNostroRefAccno())
            .setNostroTxnReferenceNo(domain.getNostroTrRefNo())
            .setNostroTxnPostBalance(domain.getNostroTrpostbalance());
  }
}
