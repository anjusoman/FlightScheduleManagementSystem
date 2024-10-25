# FlightStatusManagementSystem


![image](https://github.com/user-attachments/assets/bccc5fb7-9a06-40ed-b314-2f6576aaca2f)

## Tech Stack Decisions:

1. Amazon DynamoDB: a cloud-based, NoSQL database service. Amazon DynamoDB was selected as the storage option because of its ability to handle nested data structures, which is how the database schema was designed.
2. Java Spring Boot: an open source Java-based framework used to create microservice applications. Spring Boot was selected as the framework due to its simplicity and ability for handling more complex workflows.
3. Amazon Eventbridge: a serverless event bus. Amazon Eventbridge was selected, because it allows for increased acalability of a microservice arhcitecture by supporting event driven processing. Once a flight status is changed, an event can be published to the event bus. Other services can then ingest this change. In this way, including Eventbridge in this design facilitates better decoupling of services.
4. Amazon Lambda: a serverless computing service. Amazon Lambda was selected for the services consuming the changes from the event bus, because these services are not resource-intensive nor complex. In addition, Lambda integrates well with Eventbridge.
5. Flask: a python-based web framework. Flask was selected as a web framework for the dashboards because of its simplicity and its simple integration with websockets.
