package org.pragma.creditya.usecase.loanreport;

import lombok.RequiredArgsConstructor;
import org.pragma.creditya.model.loanreport.LoanReport;
import org.pragma.creditya.model.loanreport.exception.LoanReportNotFoundDomainException;
import org.pragma.creditya.model.loanreport.gateways.LoanReportRepository;
import org.pragma.creditya.model.loanreport.valueobject.Amount;
import org.pragma.creditya.model.loanreport.valueobject.LoanReportId;
import org.pragma.creditya.usecase.loanreport.command.UpdateReportLoanApprovedCommand;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class LoanReportUseCase implements ILoanReportUseCase {

    private final LoanReportRepository repository;

    @Override
    public Mono<LoanReport> getReportLoanApproved() {
        return repository.getReport(new LoanReportId("LOAN"))
                .switchIfEmpty(Mono.error(new LoanReportNotFoundDomainException("Report is not found")));
    }

    @Override
    public Mono<Void> updateReportLoanApproved(UpdateReportLoanApprovedCommand command) {
        return repository.getReport(new LoanReportId(command.pk()))
                .switchIfEmpty(loanReportInit(command))
                .map(domain -> {
                    domain.updateReport(new Amount(command.amount()));
                    return domain;
                })
                .flatMap(repository::updateReport)
                .then();
    }

    private Mono<LoanReport> loanReportInit (UpdateReportLoanApprovedCommand command) {
        LoanReport domain = LoanReport.Builder.any()
                .id(command.pk())
                .name(command.sk())
                .count(0)
                .build();

        return Mono.just(domain);
    }
}
