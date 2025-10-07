package org.pragma.creditya.sqs.listener;

import lombok.RequiredArgsConstructor;
import org.pragma.creditya.sqs.listener.loan.config.LoanApprovedProperties;
import org.pragma.creditya.sqs.listener.loan.mapper.LoanApprovedMapper;
import org.pragma.creditya.usecase.loanreport.ILoanReportUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.Message;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SQSProcessor implements Function<Message, Mono<Void>> {

    private final ILoanReportUseCase useCase;
    private final LoanApprovedMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(SQSProcessor.class);

    @Override
    public Mono<Void> apply(Message message) {
        logger.info("[infra.sqs-listener] (apply) payload=[ body:{} ]", message.body());

        return Mono.fromCallable(() -> mapper.toCommand(message.body()))
                .doOnSuccess(command -> logger.info("[infra.sqs-listener] (apply) build command:{}", command))
                .flatMap(useCase::updateReportLoanApproved)
                .doOnSuccess(response -> logger.info("[infra.sqs-listener] (apply) report was updated"))
                .doOnError(err -> logger.error("[infra.sqs-listener] (apply) report error, response=[ error:{}] ", err.getMessage()))
                .then();
    }
}
