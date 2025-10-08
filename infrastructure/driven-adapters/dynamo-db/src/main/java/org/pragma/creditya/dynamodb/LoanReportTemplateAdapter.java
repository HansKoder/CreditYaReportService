package org.pragma.creditya.dynamodb;

import org.pragma.creditya.dynamodb.helper.TemplateAdapterOperations;
import org.pragma.creditya.model.loanreport.LoanReport;
import org.pragma.creditya.model.loanreport.gateways.LoanReportRepository;
import org.pragma.creditya.model.loanreport.valueobject.LoanReportId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            String, /* PK (K)*/
            LoanReportEntity /*adapter model (V)*/
        > implements LoanReportRepository /* implements Gateway from domain */ {

    private final Logger logger = LoggerFactory.getLogger(LoanReportTemplateAdapter.class);

    public LoanReportTemplateAdapter(
            DynamoDbEnhancedAsyncClient connectionFactory,
            LoanReportMapper mapper) {
        super(
                connectionFactory,
                mapper,
                mapper::toEntity, // d -> mapper.toData(d, Object.class /*domain model*/),
                "reports" /* table_name*/
                 /*index is optional*/);
    }

    public Mono<List<LoanReport>> getEntityBySomeKeys(String partitionKey, String sortKey) {
        QueryEnhancedRequest queryExpression = generateQueryExpression(partitionKey, sortKey);
        return query(queryExpression);
    }

    private QueryEnhancedRequest generateQueryExpression(String partitionKey, String sortKey) {
        return QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.keyEqualTo(Key.builder().partitionValue(partitionKey).build()))
                .queryConditional(QueryConditional.sortGreaterThanOrEqualTo(Key.builder().sortValue(sortKey).build()))
                .build();
    }

    @Override
    public Mono<LoanReport> getReport(LoanReportId pk) {
        logger.info("[infra.adapter.dynamodb] (getReport) payload=[ pk:{} ]", pk);
        return this.getById(pk.getValue())
                .doOnSuccess(response -> logger.info("[infra.adapter.dynamodb] (getReport) success, response=[ entity:{} ]", response))
                .doOnError(err -> logger.info("[infra.adapter.dynamodb] (getReport) error, response=[ error:{} ]", err.getMessage()));
    }

    @Override
    public Mono<LoanReport> updateReport(LoanReport entity) {
        logger.info("[infra.adapter.dynamodb] (updateReport) payload=[ entity:{} ]", entity);
        return save(entity)
                .doOnSuccess(response -> logger.info("[infra.adapter.dynamodb] (updateReport) success, response=[ entity:{} ]", response))
                .doOnError(err -> logger.info("[infra.adapter.dynamodb] (updateReport) error, response=[ error:{} ]", err.getMessage()));
    }
}
