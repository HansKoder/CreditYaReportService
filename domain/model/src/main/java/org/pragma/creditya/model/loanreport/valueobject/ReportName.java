package org.pragma.creditya.model.loanreport.valueobject;

import org.pragma.creditya.model.loanreport.exception.LoanReportDomainException;

public record ReportName(String value) {

    public ReportName {
        if (value == null)
            throw new LoanReportDomainException("Count must be mandatory");

    }

}
