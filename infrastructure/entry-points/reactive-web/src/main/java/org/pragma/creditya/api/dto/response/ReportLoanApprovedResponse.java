package org.pragma.creditya.api.dto.response;

public record ReportLoanApprovedResponse (
    String reportName,
    Integer totalApprovedLoans
) { }
