package com.mislbd.ababil.foreignremittance.controller;

import com.mislbd.ababil.foreignremittance.domain.RemittanceMsgDto;
import com.mislbd.ababil.foreignremittance.service.SwiftMsgService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class SwiftMsgController {

  private final SwiftMsgService swiftMsgService;

  public SwiftMsgController(SwiftMsgService swiftMsgService) {
    this.swiftMsgService = swiftMsgService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<PagedResult<RemittanceMsgDto>> getMessages(
      Pageable pageable,
      @RequestParam(required = false) String msgType,
      @RequestParam(required = false) String lcNo,
      @RequestParam(required = false) BigDecimal amount,
      @RequestParam(required = false) LocalDate valueDate) {

    PagedResult<RemittanceMsgDto> pagedMessages =
        (PagedResult<RemittanceMsgDto>)
            swiftMsgService.findAll(pageable, msgType, lcNo, amount, valueDate);
    return ResponseEntity.ok(pagedMessages);
  }
}
