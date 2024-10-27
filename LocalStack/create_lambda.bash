#!/usr/bin/env bash
# Usage: ./create_lambda.bash <file_or_dir> <endpoint> <handler> <function_name> <role> <runtime>
set -e

function create_lambda() {
  # Get the arguments passed to the script
  local zip_file=$1  
  local endpoint=$2
  local function_handler=$3
  local function_name=$4
  local function_role=$5
  local function_runtime=$6

  # Validate if all arguments are provided
  if [ $# -ne 6 ]; then
    echo "Usage: $0 <file_or_dir> <endpoint> <handler> <function_name> <role> <runtime>"
    exit 1
  fi


  # Delete existing Lambda function in LocalStack if it exists
  aws --endpoint-url "$endpoint" lambda delete-function --function-name "$function_name" || true

  # Create Lambda function in LocalStack using the zip file
  aws --endpoint-url "$endpoint" lambda create-function \
    --zip-file fileb://"$zip_file" \ # Treat file as binary content
    --function-name "$function_name" \
    --handler "$function_handler" \
    --role "$function_role" \
    --runtime "$function_runtime"

  # Clean up the zip file after creating the Lambda
  rm "$zip_file"
}

# Pass all arguments from the command line to the function
create_lambda "$@"
