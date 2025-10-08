package org.pragma.creditya.dynamodb.helper;

import org.pragma.creditya.dynamodb.LoanReportTemplateAdapter;
import org.pragma.creditya.dynamodb.LoanReportEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;
import reactor.test.StepVerifier;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class TemplateAdapterOperationsTest {

    @Mock
    private DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient;

    @Mock
    private ObjectMapper mapper;

    @Mock
    private DynamoDbAsyncTable<LoanReportEntity> customerTable;

    private LoanReportEntity loanReportEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(dynamoDbEnhancedAsyncClient.table("table_name", TableSchema.fromBean(LoanReportEntity.class)))
                .thenReturn(customerTable);

        loanReportEntity = new LoanReportEntity();
        loanReportEntity.setId("id");
        loanReportEntity.setCount(0);
    }

    @Test
    void modelEntityPropertiesMustNotBeNull() {
        LoanReportEntity loanReportEntityUnderTest = new LoanReportEntity("loan-report", "approved", 0);

        assertNotNull(loanReportEntityUnderTest.getId());
        assertNotNull(loanReportEntityUnderTest.getCount());
    }

    @Test
    void testSave() {
        when(customerTable.putItem(loanReportEntity)).thenReturn(CompletableFuture.runAsync(()->{}));
        when(mapper.map(loanReportEntity, LoanReportEntity.class)).thenReturn(loanReportEntity);

        LoanReportTemplateAdapter loanReportTemplateAdapter =
                new LoanReportTemplateAdapter(dynamoDbEnhancedAsyncClient, mapper);

        StepVerifier.create(loanReportTemplateAdapter.save(loanReportEntity))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void testGetById() {
        String id = "id";

        when(customerTable.getItem(
                Key.builder().partitionValue(AttributeValue.builder().s(id).build()).build()))
                .thenReturn(CompletableFuture.completedFuture(loanReportEntity));
        when(mapper.map(loanReportEntity, Object.class)).thenReturn("value");

        LoanReportTemplateAdapter loanReportTemplateAdapter =
                new LoanReportTemplateAdapter(dynamoDbEnhancedAsyncClient, mapper);

        StepVerifier.create(loanReportTemplateAdapter.getById("id"))
                .expectNext("value")
                .verifyComplete();
    }

    @Test
    void testDelete() {
        when(mapper.map(loanReportEntity, LoanReportEntity.class)).thenReturn(loanReportEntity);
        when(mapper.map(loanReportEntity, Object.class)).thenReturn("value");

        when(customerTable.deleteItem(loanReportEntity))
                .thenReturn(CompletableFuture.completedFuture(loanReportEntity));

        LoanReportTemplateAdapter loanReportTemplateAdapter =
                new LoanReportTemplateAdapter(dynamoDbEnhancedAsyncClient, mapper);

        StepVerifier.create(loanReportTemplateAdapter.delete(loanReportEntity))
                .expectNext("value")
                .verifyComplete();
    }
}