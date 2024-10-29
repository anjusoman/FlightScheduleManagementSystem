# FlightStatusManagementSystem


![image](https://github.com/user-attachments/assets/71e44673-0461-4617-9b4d-6fe423fbbba7)


## Tech Stack Decisions:

1. Amazon DynamoDB: a cloud-based, NoSQL database service. Amazon DynamoDB was selected as the storage option because of its ability to handle nested data structures, which is how the database schema was designed. There is also extensive support for integrating it with a spring boot application through the aws SDK.
2. Java Spring Boot: an open source Java-based framework used to create microservice applications. Spring Boot was selected as the framework due to its simplicity and ability for handling more complex workflows.
3. Amazon Eventbridge: a serverless event bus. Amazon Eventbridge was selected, because it allows for increased scalability of a microservice architecture by supporting event driven processing. Once a flight status is changed, an event can be published to the event bus. The event bus will then analyze the event to determine which rules it follows and then send the events to the targets that are set for those rules. In this way, including Eventbridge in this design facilitates better decoupling of services.
4. Amazon Lambda: a serverless computing service. Although Lambda is not needed in this architecture, I wanted to learn how to use it, so it was included as a service which recieves events from the Eventbridge event bus and notifies the Flask dashboard of the change though a POST request. 
5. Flask: a python-based web framework. Flask was selected as a web framework for the dashboards because of its simplicity and its integration with websockets.
6. Docker: docker was used to containerize all components.
7. Localstack: the Amazon Eventbridge and Lambda components were set up locally using a Localstack docker container as this project is specifically for learning/development purposes and is not meant to be deployed.

## Run the project:

In order to run this project, use the docker-compose up command. 

## Demo:
![FlightScheduleManagementSystemDemo](https://github.com/user-attachments/assets/c28c9016-aa5b-443d-8637-7a93533ecbf9)

This dashboard is being updated in real time as events are scheduled by the scheduling service. The work flow is as follows:
1. The scheduling service schedules a flight. It stores it in the DynamoDB database and then puts an event on the eventbus that corresponds to the newly scheduled flight.
2. The event bus routes the new event to the lambda function which is a target for any event that is emitted by the scheduling service.
3. The lambda function recieves the new flight and sends it to the Flask dashboard through a POST request.
4. The Flask frontend uses websockets to update the client's browser in real time.



