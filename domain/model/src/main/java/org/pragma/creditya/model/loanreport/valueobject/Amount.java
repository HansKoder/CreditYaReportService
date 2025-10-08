package org.pragma.creditya.model.loanreport.valueobject;

import org.pragma.creditya.model.loanreport.exception.LoanReportDomainException;

import java.math.BigDecimal;

public record Amount (BigDecimal value) {
    public Amount {
        if (value == null)
            throw new LoanReportDomainException("Amount must be mandatory");

        if (!isGreaterOrEqualThanZero(value))
            throw new LoanReportDomainException("Amount must be positive");
    }

    private boolean isGreaterOrEqualThanZero(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) >= 0;
    }

    public Amount plus (Amount loanApproved) {
        return new Amount(value.add(loanApproved.value));
    }
}
