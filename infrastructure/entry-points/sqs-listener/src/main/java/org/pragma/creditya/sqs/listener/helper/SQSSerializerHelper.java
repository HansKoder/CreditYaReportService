package org.pragma.creditya.sqs.listener.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.pragma.creditya.sqs.listener.loan.payload.LoanApprovedPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class SQSSerializerHelper {

    private final ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(SQSSerializerHelper.class);

    public LoanApprovedPayload deserialize (String messageBody) {
        try {
            logger.info("[infra.entrypoint.sqs-listener] deserialize init, payload=[ messageBody:{} ]", messageBody);
            return objectMapper.readValue(messageBody, LoanApprovedPayload.class);
        } catch (Exception e) {
            logger.error("[infra.entrypoint.sqs-listener] deserialize error, response=[ error:{} ]", e.getMessage());
            throw new RuntimeException("Error deserializing ", e);
        }
    }

}

