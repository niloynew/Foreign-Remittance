package com.mislbd.ababil.foreignremittance.repository.schema;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/** Created by Mahbub islam on 11/16/17. */

@Entity
@Table(name = "PROD_GENERATED_ACCOUNT")
public class GeneratedAccountEntity {

    @Id
    private long id;
    private long branchId;
    private long productId;
    private String accountNumber;
    private boolean reserved;
    private LocalDateTime reservationEndsOn;
    private String reservedBy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public LocalDateTime getReservationEndsOn() {
        return reservationEndsOn;
    }

    public void setReservationEndsOn(LocalDateTime reservationEndsOn) {
        this.reservationEndsOn = reservationEndsOn;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }

}
