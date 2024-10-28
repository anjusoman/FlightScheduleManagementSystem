# Use the latest LocalStack image
FROM localstack/localstack:latest

# Set environment variables
ENV DEBUG=0 \
    DOCKER_HOST=unix:///var/run/docker.sock \
    SERVICES=lambda,events \
    AWS_ACCESS_KEY_ID=FAKEID \
    AWS_SECRET_ACCESS_KEY=FAKEKEY

# Expose the necessary ports
EXPOSE 4510-4559 4566

# Copy your setup and lambda creation scripts into the container
COPY ./setup_localstack.sh /etc/localstack/init/ready.d/startup.sh
COPY ./lambda_functions/lambda_function.zip /opt/code/localstack/lambda_function.zip

# Ensure the scripts are executable
RUN chmod +x /etc/localstack/init/ready.d/startup.sh
