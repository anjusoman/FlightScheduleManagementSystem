# FlightStatusManagementSystem


![image](https://github.com/user-attachments/assets/71e44673-0461-4617-9b4d-6fe423fbbba7)


## Tech Stack Decisions:

1. Amazon DynamoDB: a cloud-based, NoSQL database service. Amazon DynamoDB was selected as the storage option because of its ability to handle nested data structures, which is how the database schema was designed. There is also extensive support for integrating it with a spring boot application through the aws SDK.
2. Java Spring Boot: an open source Java-based framework used to create microservice applications. Spring Boot was selected as the framework due to its simplicity and ability for handling more complex workflows.
3. Amazon Eventbridge: a serverless event bus. Amazon Eventbridge was selected, because it allows for increased acalability of a microservice arhcitecture by supporting event driven processing. Once a flight status is changed, an event can be published to the event bus. Other services can then ingest this change. In this way, including Eventbridge in this design facilitates better decoupling of services.
4. Amazon Lambda: a serverless computing service. Amazon Lambda was selected for the service recieving events from the eventbus, because the service is simple an simply needs to make the neccessary updates in the dashboard. In addition, Lambda integrates well with Eventbridge.
5. Flask: a python-based web framework. Flask was selected as a web framework for the dashboards because of its simplicity and its integration with websockets.
6. Docker: docker was used to containerize all components.
7. Localstack: the Amazon Eventbridge and Lambda components were set up locally using a Localstack docker container as this project is specifically for learning/development purposes and is not meant to be deployed.

## Run the project:

In order to run this project, use the docker-compose up command. 


