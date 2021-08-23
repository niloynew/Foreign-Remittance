package com.mislbd.ababil.foreignremittance.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class SwiftBankConfiguration {

    private long id;


    private long bankTypeId;


    private long bankId;


    private String bankTypeName;


    private String bankName;


    private String swiftCode;


    private long branchId;


    private String branchName;




}
