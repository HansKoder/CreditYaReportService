package org.pragma.creditya.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.math.BigDecimal;

@DynamoDbBean
public class LoanReportEntity {

    private String id;
    private String name;
    private BigDecimal totalAmountApproved;
    private Integer count;

    // Constructor
    public LoanReportEntity() {
    }

    // Attributes
    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDbAttribute("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDbAttribute("count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @DynamoDbAttribute("total_amount_approved")
    public BigDecimal getTotalAmountApproved() {
        return totalAmountApproved;
    }

    public void setTotalAmountApproved(BigDecimal totalAmountApproved) {
        this.totalAmountApproved = totalAmountApproved;
    }

    // Builder

    public static final class Builder {
        private Integer count;
        private String id;
        private String name;
        private BigDecimal totalAmountApproved;

        private Builder() {
        }

        public static Builder any() {
            return new Builder();
        }

        public Builder count(Integer count) {
            this.count = count;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder totalAmountApproved(BigDecimal totalAmountApproved) {
            this.totalAmountApproved = totalAmountApproved;
            return this;
        }

        public LoanReportEntity build() {
            LoanReportEntity loanReportEntity = new LoanReportEntity();
            loanReportEntity.setCount(count);
            loanReportEntity.setId(id);
            loanReportEntity.setName(name);
            loanReportEntity.setTotalAmountApproved(totalAmountApproved);
            return loanReportEntity;
        }
    }


    @Override
    public String toString() {
        return "LoanReportEntity{" +
                "count=" + count +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", totalAmountApproved=" + totalAmountApproved +
                '}';
    }

}
