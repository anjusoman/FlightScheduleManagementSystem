docker compose up -d

chmod +x LocalStack/setup_localstack.sh
./LocalStack/setup_localstack.sh

./LocalStack/create_lambda.bash ./FlightStatusService/lambda_function.py http://localhost:4566 lambda_function.lambda_handler hello-world-python arn:aws:iam::000000000000:role/localstack-does-not-care python3.9
