package org.pragma.creditya.model.loanreport;

import lombok.Getter;
import lombok.ToString;
import org.pragma.creditya.model.loanreport.valueobject.Amount;
import org.pragma.creditya.model.loanreport.valueobject.Count;
import org.pragma.creditya.model.loanreport.valueobject.LoanReportId;
import org.pragma.creditya.model.loanreport.valueobject.ReportName;
import org.pragma.creditya.model.shared.domain.model.entity.AggregateRoot;

import java.math.BigDecimal;

@Getter
@ToString
public class LoanReport extends AggregateRoot<LoanReportId> {

    private final ReportName name;
    private Count count;
    private Amount totalApprovedAmount;

    private LoanReport(Builder builder) {
        this.setId(builder.id);
        this.name = builder.name;
        this.count = builder.count;
        this.totalApprovedAmount = builder.totalApprovedAmount;
    }

    // Business Rules
    public void updateReport (Amount amountApproved) {
        System.out.println("[domain.report] (updateReport) count init payload=[ count:{" + count + "}, totalApprovedAmount:{" + totalApprovedAmount + "}]");
        count = count.increment();
        totalApprovedAmount = totalApprovedAmount.plus(amountApproved);
        System.out.println("[domain.report] (updateReport) count incremented response=[ count:{" + count + "}, totalApprovedAmount:{" + totalApprovedAmount + "}]");
    }

    // Builder
    public static final class Builder {
        private Count count;
        private ReportName name;
        private LoanReportId id;
        private Amount totalApprovedAmount;

        private Builder() {
        }

        public static Builder any() {
            return new Builder();
        }

        public Builder count(Integer count) {
            this.count = new Count(count);
            return this;
        }

        public Builder name(String name) {
            this.name = new ReportName(name);
            return this;
        }

        public Builder id(String id) {
            this.id = new LoanReportId(id);
            return this;
        }

        public Builder totalApprovedAmount(BigDecimal value) {
            this.totalApprovedAmount = new Amount(value);
            return this;
        }

        public LoanReport build() {
            return new LoanReport(this);
        }

    }

}
