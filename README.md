
# Local test report service

## Running localstack

## Create a docker-compose.yml and put this code

```cmd
services:

  localstack:
    image: localstack/localstack
    container_name: localstack
    ports:
      - "4566:4566"
      - "4571:4571"
    environment:
      - SERVICES=lambda,cloudformation,sqs,ses,dynamodb
      - DEBUG=1
      - DATA_DIR=/tmp/localstack/data
      - LAMBDA_EXECUTOR=docker
    volumes:
      - "/var/run/docker.sock:/var/run/docker.sock"
      - "./.localstack-data:/var/lib/localstack"

```

## Running docker using intelli IDE CE or command 

```cmd
docker-compose up -d
```


## SQS reports

```bash
aws sqs create-queue \
  --queue-name reports-queue \
  --endpoint-url http://localhost:4566 \
  --region us-east-2

```

## SQS list all queues

```cmd
aws sqs list-queues \
  --endpoint-url http://localhost:4566 \
  --region us-east-2
```

## SQS example send manual reports-queue

```cmd
aws sqs send-message \
  --queue-url http://localhost:4566/000000000000/reports-queue \
  --message-body '{
    "loanId": "001",
    "amount": 200
  }' \
  --endpoint-url http://localhost:4566 \
  --region us-east-2

```


## SQS remove queues notifications

```cmd
aws sqs purge-queue \
  --queue-url http://sqs.us-east-2.localhost.localstack.cloud:4566/000000000000/reports-queue \
  --endpoint-url http://localhost:4566 \
  --region us-east-2
```




## DynamoDB reports

```bash
aws dynamodb create-table \
  --table-name reports \
  --attribute-definitions \
  AttributeName=id,AttributeType=S \
  --key-schema \
  AttributeName=id,KeyType=HASH \
  --provisioned-throughput \
  ReadCapacityUnits=5,WriteCapacityUnits=5 \
  --endpoint-url=http://localhost:4566 \
  --region=us-east-2

```

## DynamoDB reports list tables

```bash
aws dynamodb list-tables --endpoint-url=http://localhost:4566
```

## DynamoDB reports describe 

```bash
aws dynamodb describe-table \
  --table-name reports \
  --endpoint-url http://localhost:4566

```

## DynamoDB reports get data

```bash
aws dynamodb scan \
  --table-name reports \
  --endpoint-url http://localhost:4566

```