###Customer Accounts Aggregation Application

#####SpringBoot application which has the scope to expose data received from another provider. 

Application has 2 main functionalities:

#####Retrieve data:
- On application start-up an update is being performed, _"executeInitialUpdate()"_ method is being executed, as some data is required in order to test the application.
- Daily data update is being performed at 01:00 AM, every day, (configurable based on a cron expression specified in application.properties)
- Daily data update is being performed for specific user (configurable based on _update.on.behalf.of.user_ property in application.properties) 
- wiremock url is configurable from application.properties (default to _http://localhost:8080_)
- Due to the requirement(_the faster, the better_), retrieved data is being saved in a HashMap (key - username) and not in a DB (also because the amount of data is not that large)
- Basic exception handling implemented
- Data retrieval required a running wiremock instance.

#####Expose data:
- default port 8088 (configurable in application.properties)
- GET /data/account/{username}
- GET /data/transactions/{username}


####Build & run
run _mvn spring-boot:run_ command in the root directory of the project 

Build standalone jar application: run mvn clean install in the root directory of the project. Generated Jar will be available in target folder inside the project.
