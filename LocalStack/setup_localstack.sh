#!/bin/bash

# Configure AWS CLI for LocalStack
aws configure --profile localstack <<EOF
FAKEID
FAKEKEY
us-east-2
json
EOF

# Set environment variables
export AWS_DEFAULT_REGION=us-east-2
export LOCALSTACK_URL=http://0.0.0.0:4566
export AWS_PROFILE=localstack
export AWS_ROLE=arn:aws:iam::000000000000:role/localstack-does-not-care

chmod +x ./create_lambda.bash
./create_lambda.bash ./FlightStatusService/lambda_function.py http://localhost:4566 lambda_function.lambda_handler hello-world-python python3.9

