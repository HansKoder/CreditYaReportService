package org.pragma.creditya.model.loanreport.valueobject;

import org.pragma.creditya.model.loanreport.exception.LoanReportDomainException;

public record Count (Integer value) {

    public Count {
        if (value == null)
            throw new LoanReportDomainException("Count must be mandatory");

        if (value < 0)
            throw new LoanReportDomainException("Count must be positive");

    }

    public Count increment () {
        System.out.println("Increment current value " + value + " should be incremented");
        return new Count(value + 1);
    }

}
