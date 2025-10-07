package org.pragma.creditya.usecase.loanreport;

import lombok.RequiredArgsConstructor;
import org.pragma.creditya.model.loanreport.LoanReport;
import org.pragma.creditya.model.loanreport.exception.LoanReportNotFoundDomainException;
import org.pragma.creditya.model.loanreport.gateways.LoanReportRepository;
import org.pragma.creditya.model.loanreport.valueobject.LoanReportId;
import org.pragma.creditya.usecase.loanreport.command.UpdateReportLoanApprovedCommand;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class LoanReportUseCase implements ILoanReportUseCase {

    private final LoanReportRepository repository;

    @Override
    public Mono<LoanReport> getReportLoanApproved() {
        return null;
    }

    @Override
    public Mono<Void> updateReportLoanApproved(UpdateReportLoanApprovedCommand command) {
        return repository.getReport(new LoanReportId(command.reportNamePK()))
                .switchIfEmpty(Mono.error(new LoanReportNotFoundDomainException("Report " + command.reportNamePK() + " is not found")))
                .then();
    }
}
