package com.mislbd.ababil.foreignremittance.command;

import com.mislbd.ababil.foreignremittance.domain.IDProduct;
import com.mislbd.asset.command.api.Command;

public class CreateIDProductCommand extends Command<IDProduct> {

  public CreateIDProductCommand(IDProduct payload) {
    super(payload);
  }
}
