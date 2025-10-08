package org.pragma.creditya.api;

import lombok.RequiredArgsConstructor;
import org.pragma.creditya.api.mapper.RestMapper;
import org.pragma.creditya.usecase.loanreport.ILoanReportUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final ILoanReportUseCase useCase;

    private final Logger logger = LoggerFactory.getLogger(Handler.class);

    public Mono<ServerResponse> getReportLoanApproved(ServerRequest serverRequest) {
        logger.info("[infra.entrypoint.handler] (getReportLoanApproved) init");
        return useCase.getReportLoanApproved()
                .map(RestMapper::toResponse)
                .doOnSuccess(response -> logger.info("[infra.entrypoint.handler] (getReportLoanApproved) get report, response=[ report:{} ]", response))
                .flatMap(data -> ServerResponse.ok().bodyValue(data));
    }

}
