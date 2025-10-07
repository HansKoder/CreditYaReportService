package org.pragma.creditya.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

/* Enhanced DynamoDB annotations are incompatible with Lombok #1932
         https://github.com/aws/aws-sdk-java-v2/issues/1932*/
@DynamoDbBean
public class ModelEntity {

    private String id;
    private Integer count;

    public ModelEntity() {
    }

    public ModelEntity(String id, Integer count) {
        this.id = id;
        this.count = count;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("name")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDbAttribute("count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
