# REST service to assess security and privacy

This Service can be used to assess limited [security and privacy feature](https://docs.google.com/document/d/1DrXVIkNJstshpFowviK1szww5F9Q-O6KeGvLZ5Kjdvk).

The service offers one method under ``POST /filter`` more information can be found under ``./api/``. 

## Requirements

* Apache Maven 3
* jre 8 
* Tomcat 8

## Execution

Download the Project and  run 
```
mvn clean install
java  -jar  target/privacySecurityEvaluator-0.0.1-SNAPSHOT.jar 
```