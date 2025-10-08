package org.pragma.creditya.dynamodb;

import org.pragma.creditya.dynamodb.helper.CustomMapper;
import org.pragma.creditya.dynamodb.helper.TemplateAdapterOperations;
import org.pragma.creditya.model.loanreport.LoanReport;
import org.pragma.creditya.model.loanreport.gateways.LoanReportRepository;
import org.pragma.creditya.model.loanreport.valueobject.LoanReportId;
import org.pragma.creditya.model.loanreport.valueobject.ReportNameSK;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.List;


@Repository
public class LoanReportTemplateAdapter
        extends TemplateAdapterOperations<
            LoanReport /*domain model  (E)*/,
            String, /* PK (K) */
            LoanReportEntity /*adapter model (V) */
        > implements LoanReportRepository /* implements Gateway from domain */ {

    public LoanReportTemplateAdapter(DynamoDbEnhancedAsyncClient connectionFactory, CustomMapper<LoanReport, LoanReportEntity> mapper) {
        super(
                connectionFactory,
                mapper,
                mapper::toEntity, // d -> mapper.toData(d, Object.class /*domain model*/),
                "reports" /* table_name*/,
                "" /*index is optional*/);
    }

    public Mono<List<LoanReport>> getEntityBySomeKeys(String partitionKey, String sortKey) {
        QueryEnhancedRequest queryExpression = generateQueryExpression(partitionKey, sortKey);
        return query(queryExpression);
    }

    public Mono<List<LoanReport>> getEntityBySomeKeysByIndex(String partitionKey, String sortKey) {
        QueryEnhancedRequest queryExpression = generateQueryExpression(partitionKey, sortKey);
        return queryByIndex(queryExpression, "secondary_index" /*index is optional if you define in constructor*/);
    }

    private QueryEnhancedRequest generateQueryExpression(String partitionKey, String sortKey) {
        return QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.keyEqualTo(Key.builder().partitionValue(partitionKey).build()))
                .queryConditional(QueryConditional.sortGreaterThanOrEqualTo(Key.builder().sortValue(sortKey).build()))
                .build();
    }

    @Override
    public Mono<LoanReport> getReport(LoanReportId pk, ReportNameSK sk) {
        return null;
    }

    @Override
    public Mono<LoanReport> updateReport(LoanReportId pk, ReportNameSK sk) {
        return null;
    }
}
