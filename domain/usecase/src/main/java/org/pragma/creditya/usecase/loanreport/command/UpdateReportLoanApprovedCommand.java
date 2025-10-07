package org.pragma.creditya.usecase.loanreport.command;

import java.math.BigDecimal;

public record UpdateReportLoanApprovedCommand(
        String reportNamePK,
        BigDecimal amount
) {}
