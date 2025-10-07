package org.pragma.creditya.sqs.listener.loan.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "report.loan-approved")
public record LoanApprovedProperties (
        String reportNamePK
) { }
