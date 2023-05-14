# patient-visit-service

## Main REST API functionalities
This project provides the following functionalities as REST API:
- Create Patient. 
- Get All Patient
- Get Patient by id
- Create a Visit for a Patient.
- Update a Visit of a patient.
- Get All Visits.
- Get Visit by id.
- Get All visits by Patient id.

## Input Validation Rules
This project focuses on Input validation, exception handling and returning meaningful error message. Some of the validation rules are the following:
- LocalDate type is used for BirthDate and follows the following format "yyyy-MM-dd" an example is "1990-01-01".
- ZonedDateTime type is used for the visit date and follows the following format "yyyy-MM-dd'T'HH:mm:ss.SSSZ" an example is "2023-05-16T01:47:48.688+0200".
- For the Patient Social Security Number field the format is the following according to German Security Number convention [0-9]{8}[A-Z][0-9]{3} an example is 12345678A111.
- For the visit date. The assumption is each visit should take around 15 min. As a result if a patient wants to make a new visit the date should respect other appointment made by him to avoid collusion between visits.

## Correlation id and utilities
Additionally, a request correlation id will be added to each request logs. To improve traceability and facilitate debugging.
A postman collection can be found at the root directory of this project named "CGM.postman_collection.json" feel free to import it to facilitate the interaction with the REST API.
Also, when you start the project locally it is possible to check the openAPI specification on the following link: https://localhost:8080/swagger-ui
## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```
