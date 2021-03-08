package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.swift.broker.model.raw.NostroTransaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShadowTransactionRecordList {
    private List<ShadowTransactionRecord> shadowTransactionRecords;
}
