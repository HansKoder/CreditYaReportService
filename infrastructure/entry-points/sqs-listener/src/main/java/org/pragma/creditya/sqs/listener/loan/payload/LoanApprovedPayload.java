package org.pragma.creditya.sqs.listener.loan.payload;

import java.math.BigDecimal;

public record LoanApprovedPayload (
    String loanId,
    BigDecimal amount
) { }
