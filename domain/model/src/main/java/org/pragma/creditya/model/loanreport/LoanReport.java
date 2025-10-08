package org.pragma.creditya.model.loanreport;

import lombok.Data;
import org.pragma.creditya.model.loanreport.valueobject.Count;
import org.pragma.creditya.model.loanreport.valueobject.LoanReportId;
import org.pragma.creditya.model.loanreport.valueobject.ReportNameSK;
import org.pragma.creditya.model.shared.domain.model.entity.AggregateRoot;

public class LoanReport extends AggregateRoot<LoanReportId> {

    private ReportNameSK name;
    private Count count;

    private LoanReport(Builder builder) {
        this.setId(builder.id);
        this.setName(builder.name);
        this.setCount(builder.count);
    }

    // Business Rules
    private void updateReport () {
        count =  count.increment();
    }

    // Getters and Setters
    public Count getCount() {
        return count;
    }

    public void setCount(Count count) {
        this.count = count;
    }

    public ReportNameSK getName() {
        return name;
    }

    public void setName(ReportNameSK name) {
        this.name = name;
    }

    // Builder
    public static final class Builder {
        private Count count;
        private ReportNameSK name;
        private LoanReportId id;

        private Builder() {
        }

        public static Builder aLoanReport() {
            return new Builder();
        }

        public Builder count(Integer count) {
            this.count = new Count(count);
            return this;
        }

        public Builder name(String name) {
            this.name = new ReportNameSK(name);
            return this;
        }

        public Builder id(String id) {
            this.id = new LoanReportId(id);
            return this;
        }

        public LoanReport build() {
            return new LoanReport(this);
        }
    }

    @Override
    public String toString() {
        return "LoanReport{" +
                "count=" + count +
                ", name='" + name + '\'' +
                '}';
    }
}
