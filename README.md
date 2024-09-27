# api-store

## Description

api-store microservice

## Run in local environment

-Dspring.profiles.active=local

## DynamoDB model

````
aws dynamodb --endpoint-url http://localhost:1221 create-table \
    --table-name local-mb-api-sign \
    --attribute-definitions \
        AttributeName=envelopeId,AttributeType=S \
    --key-schema \
        AttributeName=envelopeId,KeyType=HASH \
    --provisioned-throughput \
        ReadCapacityUnits=90,WriteCapacityUnits=180
````
