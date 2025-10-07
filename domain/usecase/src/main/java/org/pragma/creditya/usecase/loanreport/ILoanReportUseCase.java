package org.pragma.creditya.usecase.loanreport;

import org.pragma.creditya.model.loanreport.LoanReport;
import org.pragma.creditya.usecase.loanreport.command.UpdateReportLoanApprovedCommand;
import reactor.core.publisher.Mono;

public interface ILoanReportUseCase {

    Mono<LoanReport> getReportLoanApproved ();

    Mono<Void> updateReportLoanApproved (UpdateReportLoanApprovedCommand command);

}
