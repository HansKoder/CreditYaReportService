package org.pragma.creditya.api.dto.response;

import java.math.BigDecimal;

public record ReportLoanApprovedResponse (
    String reportName,
    Integer totalLoansApproved,
    BigDecimal amountLoansApproved
) { }
