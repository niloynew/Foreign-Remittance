package com.mislbd.ababil.foreignremittance.query;

import com.mislbd.ababil.foreignremittance.domain.NostroReconcileStatus;
import com.mislbd.asset.query.api.QueryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnreconciledTransactionQuery extends QueryRequest {
	private Pageable pageable;
	private String accountNumber;
	private LocalDate fromDate;
	private LocalDate toDate;
	private NostroReconcileStatus status;
}
