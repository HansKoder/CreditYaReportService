package org.pragma.creditya.model.loanreport;

import org.pragma.creditya.model.loanreport.valueobject.Count;
import org.pragma.creditya.model.loanreport.valueobject.LoanReportId;
import org.pragma.creditya.model.loanreport.valueobject.ReportName;
import org.pragma.creditya.model.shared.domain.model.entity.AggregateRoot;

public class LoanReport extends AggregateRoot<LoanReportId> {

    private ReportName name;
    private Count count;

    private LoanReport(Builder builder) {
        this.setId(builder.id);
        this.setName(builder.name);
        this.setCount(builder.count);
    }

    // Business Rules
    public void updateReport () {
        System.out.println("[domain.report] (updateReport) count init payload=[ count:{" + count + "}]");
        count = count.increment();
        System.out.println("[domain.report] (updateReport) count incremented response=[ count:{" + count + "}]");
    }

    // Getters & Setters
    public Count getCount() {
        return count;
    }

    public void setCount(Count count) {
        this.count = count;
    }

    public ReportName getName() {
        return name;
    }

    public void setName(ReportName name) {
        this.name = name;
    }

    // Builder
    public static final class Builder {
        private Count count;
        private ReportName name;
        private LoanReportId id;

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
