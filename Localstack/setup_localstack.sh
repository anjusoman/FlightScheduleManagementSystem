#!/bin/bash

aws configure --profile localstack <<EOF
FAKEID
FAKEKEY
us-east-2
json
EOF

export AWS_PROFILE=localstack
export AWS_DEFAULT_REGION=us-east-2
export AWS_PROFILE=localstack
export LOCALSTACK_URL=http://localhost:4566/

function fail() {
    echo $2
    exit $1
}

# Create the event bus
aws --endpoint-url http://localhost:4566 events create-event-bus \
    --name EventBus \
    --description "Event bus for processing flight schedule changes" \
    --profile localstack 
[ $? == 0 ] || fail 1 "Failed: AWS / events / create-event-bus"

# Create a rule for the event bus
aws --endpoint-url http://localhost:4566 events put-rule \
    --name FlightScheduleEventRule \
    --event-bus-name EventBus \
    --event-pattern '{"source": ["scheduling.service"]}' \
    --description "Rule to match flight generation events from the scheduling service" \
    --profile localstack 
[ $? == 0 ] || fail 1 "Failed: AWS / events / put-rule"

# Create a lambda function to handle the event
aws --endpoint-url $LOCALSTACK_URL lambda create-function \
    --function-name hello \
    --runtime python3.9 \
    --handler lambda_function.lambda_handler \
    --zip-file fileb://lambda_function.zip \
    --role arn:aws:iam::000000000000:role/localstack-does-not-care \
    --profile localstack
[ $? == 0 ] || fail 1 "Failed: AWS / lambda / create-function"

function_arn=$(aws --endpoint-url $LOCALSTACK_URL lambda get-function --function-name hello --profile localstack --query 'Configuration.FunctionArn' --output text)


# Assign the lambda function as the event target
aws --endpoint-url $LOCALSTACK_URL events put-targets \
  --rule FlightScheduleEventRule \
  --event-bus-name EventBus \
  --targets "[{
      \"Id\": \"1\",
      \"Arn\": \"$function_arn\"
  }]" \
  --profile localstack
[ $? == 0 ] || fail 1 "Failed: AWS / events / put-targets"


# aws --endpoint http://localhost:4566/ lambda invoke --function-name hello --invocation-type RequestResponse --payload '{ "hello": "world" }' response.json --profile localstack
