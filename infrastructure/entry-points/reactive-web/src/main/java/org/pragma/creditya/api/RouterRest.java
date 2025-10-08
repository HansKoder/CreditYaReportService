package org.pragma.creditya.api;

import org.pragma.creditya.api.dto.response.ErrorResponse;
import org.pragma.creditya.model.loanreport.exception.LoanReportDomainException;
import org.pragma.creditya.model.loanreport.exception.LoanReportNotFoundDomainException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/api/v1/report/loans-approved"), handler::getReportLoanApproved)
                .filter(domainHandlerExceptions());
    }

    private HandlerFilterFunction<ServerResponse, ServerResponse> domainHandlerExceptions() {
        return (request, next) ->
                next.handle(request)
                        .onErrorResume(LoanReportDomainException.class, ex ->
                                ServerResponse.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()))
                        ).onErrorResume(LoanReportNotFoundDomainException.class, ex ->
                                ServerResponse.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()))
                        );
    }
}
