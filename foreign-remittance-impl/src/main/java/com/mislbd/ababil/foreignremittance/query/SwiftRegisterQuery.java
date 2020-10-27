package com.mislbd.ababil.foreignremittance.query;

import com.mislbd.asset.query.api.QueryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SwiftRegisterQuery extends QueryRequest {

    Pageable pageable;
    private boolean asPage;
    private String referenceNo;
    private String senderAddress;
    private String receiverAddress;
    private String status;
    private Date messageRoutingDateTime;

}
