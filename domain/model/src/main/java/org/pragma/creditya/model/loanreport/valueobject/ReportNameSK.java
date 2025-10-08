package org.pragma.creditya.model.loanreport.valueobject;

import org.pragma.creditya.model.loanreport.exception.LoanReportDomainException;

public record ReportNameSK (String value) {

    public ReportNameSK {
        if (value == null)
            throw new LoanReportDomainException("Count must be mandatory");

    }

}
