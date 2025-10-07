package org.pragma.creditya.usecase.loanreport;

import lombok.RequiredArgsConstructor;
import org.pragma.creditya.model.loanreport.LoanReport;
import org.pragma.creditya.usecase.loanreport.command.UpdateReportLoanApprovedCommand;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class LoanReportUseCase implements ILoanReportUseCase {
    @Override
    public Mono<LoanReport> getReportLoanApproved() {
        return null;
    }

    @Override
    public Mono<Void> updateReportLoanApproved(UpdateReportLoanApprovedCommand command) {
        return null;
    }
}
