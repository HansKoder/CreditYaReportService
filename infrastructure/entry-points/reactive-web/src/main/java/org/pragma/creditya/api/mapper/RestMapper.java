package org.pragma.creditya.api.mapper;

import org.pragma.creditya.api.dto.response.ReportLoanApprovedResponse;
import org.pragma.creditya.model.loanreport.LoanReport;

public class RestMapper {

    public static ReportLoanApprovedResponse toResponse (LoanReport domain) {
        return new ReportLoanApprovedResponse(
            domain.getId().getValue(),
            domain.getCount().value(),
            domain.getTotalApprovedAmount().value()
        );
    }

}
