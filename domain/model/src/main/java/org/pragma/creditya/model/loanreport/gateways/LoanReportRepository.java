package org.pragma.creditya.model.loanreport.gateways;

import org.pragma.creditya.model.loanreport.LoanReport;
import org.pragma.creditya.model.loanreport.valueobject.LoanReportId;
import org.pragma.creditya.model.loanreport.valueobject.ReportNameSK;
import reactor.core.publisher.Mono;

public interface LoanReportRepository {

    Mono<LoanReport> getReport (LoanReportId pk, ReportNameSK sk);

    Mono<LoanReport> updateReport (LoanReportId pk, ReportNameSK sk);

}
