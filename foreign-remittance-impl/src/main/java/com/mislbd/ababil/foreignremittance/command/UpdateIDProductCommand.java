package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.IDProduct;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.HasIdentity;

public class UpdateIDProductCommand extends Command<IDProduct> implements HasIdentity {

  private long id;

  public UpdateIDProductCommand(IDProduct payload, long id) {
    super(payload);
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String getIdentity() {
    return String.valueOf(getPayload().getId());
  }
}
