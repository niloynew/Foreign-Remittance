package com.mislbd.ababil.foreignremittance;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.mislbd.ababil.foreignremittance.external.domain.QueryTransactionStatus;
import com.mislbd.ababil.foreignremittance.external.domain.TransactionQueryResponse;
import com.mislbd.ababil.foreignremittance.external.repository.TransactionQueryClient;
import com.mislbd.ababil.foreignremittance.external.service.TransactionQueryService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

// @RunWith(MockitoJUnitRunner.class)
public class QueryTransactionServiceTest {

  @Mock TransactionQueryClient client;

  TransactionQueryService queryService;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    queryService = new TransactionQueryService(client);
  }

  @Test
  public void testGetTransactionQueryResponse() {
    List<String> vouchers = Arrays.asList("2552232012", "2552232015");
    List<TransactionQueryResponse> responses = new ArrayList<>();
    responses.add(new TransactionQueryResponse("2552232012", QueryTransactionStatus.RESOLVED));
    responses.add(new TransactionQueryResponse("2552232015", QueryTransactionStatus.NOT_FOUND));
    when(client.getQueryResponse(vouchers)).thenReturn(responses);
    List<TransactionQueryResponse> responsesFromReq = client.getQueryResponse(vouchers);
    assertTrue(!responsesFromReq.isEmpty());
  }
}
