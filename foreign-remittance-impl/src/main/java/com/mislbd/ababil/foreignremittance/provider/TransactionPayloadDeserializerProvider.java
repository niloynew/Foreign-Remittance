package com.mislbd.ababil.foreignremittance.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.transaction.api.transaction.service.TransactionPayloadDeserializer;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class TransactionPayloadDeserializerProvider implements TransactionPayloadDeserializer {

  private final ObjectMapper objectMapper;

  public TransactionPayloadDeserializerProvider(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public Object deserializeByCode(Map payload, String code) {
    String activityCode = code.substring(0, 3);

    switch (activityCode) {
      case ("805"):
      case ("806"):
        return deserialize(payload, RemittanceTransaction.class);
      default:
        throw new RuntimeException("Unknown code: " + code);
    }
  }

  private Object deserialize(Map payload, Class type) {
    return objectMapper.convertValue(payload, type);
  }
}
