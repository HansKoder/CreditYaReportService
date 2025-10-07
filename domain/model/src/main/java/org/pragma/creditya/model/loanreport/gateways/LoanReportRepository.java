package org.pragma.creditya.model.loanreport.gateways;

import org.pragma.creditya.model.loanreport.LoanReport;
import org.pragma.creditya.model.loanreport.valueobject.LoanReportId;
import reactor.core.publisher.Mono;

public interface LoanReportRepository {

    Mono<LoanReport> getReport (LoanReportId id);

    Mono<LoanReport> updateReport (LoanReportId id);

}
