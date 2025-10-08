package org.pragma.creditya.sqs.listener.loan.mapper;

import lombok.RequiredArgsConstructor;
import org.pragma.creditya.sqs.listener.helper.SQSSerializerHelper;
import org.pragma.creditya.sqs.listener.loan.config.LoanApprovedProperties;
import org.pragma.creditya.sqs.listener.loan.payload.LoanApprovedPayload;
import org.pragma.creditya.usecase.loanreport.command.UpdateReportLoanApprovedCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoanApprovedMapper {

    private final SQSSerializerHelper helper;
    private final LoanApprovedProperties loanApprovedProperties;

    private final Logger logger = LoggerFactory.getLogger(LoanApprovedMapper.class);

    public UpdateReportLoanApprovedCommand toCommand (String messageBody) {
        logger.info("[infra.sqs-listener.mapper] (toCommand) (01) payload=[ messageBody:{} ]", messageBody);
        LoanApprovedPayload payload = helper.deserialize(messageBody);

        logger.info("[infra.sqs-listener.mapper] (toCommand) (02) extract payload from messageBody Response=[ payload:{} ]", payload);

        return new UpdateReportLoanApprovedCommand(
                loanApprovedProperties.pk(),
                loanApprovedProperties.sk(),
                payload.amount()
        );
    }
}
