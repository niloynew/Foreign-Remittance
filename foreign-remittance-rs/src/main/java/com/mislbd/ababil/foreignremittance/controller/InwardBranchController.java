package com.mislbd.ababil.foreignremittance.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/inward-branch", produces = MediaType.APPLICATION_JSON_VALUE)
public class InwardBranchController {

  @GetMapping
  public ResponseEntity<?> getIDAccounts(@RequestParam(value = "branchId") String branchId) {

    return null;
  }
}
