# DITAS - PrivacySecurityEvaluator
The PSE is a service used in the blueprint selection phase of DITAS. It's responsible for filtering and ranking the [security and privacy properties](https://docs.google.com/document/d/1DrXVIkNJstshpFowviK1szww5F9Q-O6KeGvLZ5Kjdvk) of a blueprint.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
 * Java JDK 8 or later
 * Maven 3 or later

### Installing

For installation you have two options, building and running it on your local machine or using the docker approach.

For local testing and building for that you can follow the following steps:

install dependencies (only needs to be done once):

```
mvn clean install

```

compile
```
mvn build
```

to run locally:
```
mvn spring-boot:run
```

Alternatively you can build the provided docker image:
```
docker build -t ditas/privacy-security-evaluator -f Dockerfile.artifact .
```

and run it:
```
docker run -p 8080:8080 ditas/privacy-security-evaluator
```

## Running the tests

For testing you can use:
```
 mvn test
```


### API
The service offers one method under ``POST /filter`` more information can be found under ``./api/``. 

## Built With


* Apache Maven 3
* jre 8 
* Tomcat 8
* SpringBoot



## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## License

This project is licensed under the Apache 2.0 - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments

This is being developed for the [DITAS Project](https://www.ditas-project.eu/).