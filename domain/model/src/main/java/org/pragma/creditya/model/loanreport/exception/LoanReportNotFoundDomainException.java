package org.pragma.creditya.model.loanreport.exception;

import org.pragma.creditya.model.shared.domain.exception.DomainException;

public class LoanReportNotFoundDomainException extends DomainException {
    public LoanReportNotFoundDomainException(String message) {
        super(message);
    }
}
