# Introduction

The drop wizard university-foo application was developed to showcase the use of OAuth2 and provide a basic - yet dummy - REST interface for demo and workshop purposes.

# Overview

Included with this application is an in-memory implementation of a OAuth2 Provider to grant access tokens (something missing in the DropWizard example codebase)

# Running The Application

To bootstrap the example application run the following commands.

* To package the example run.

        mvn package

* To run the server run (in debug mode).

        java -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000 target/rest-example-1.0-SNAPSHOT.jar server university-foo-dev.yml 

# Testing The Application

To test the application you will need an OAuth2 client. The https://github.com/OpenConext/Mujina/tree/master/mujina-sp application can be used to test the OAuth provider in
this application. Fire up mujina-sp and point your browser to: http://localhost:9090/social/social-queries.shtml

The OAuth2 configuration for the university-foo application is:
 
* OAuth key: university-client-key
* OAuth secret: university-client-secret 
* AccessToken URL: http://localhost:8080/token
* Authorization URL: http://localhost:8080/authorize
* API Request: http://localhost:8080/student/identifier

To authenticate an user the identifiers foo1...10 can be used with a fake password.

# Limitations

* The university-foo OAuth provider currently only support the Authorization Code Grant flow (e.g. Implicit Grant and other flows are not supported).
* The absence of a consent dialog when authentication succeeds.
* The enforcement that a REST call can only access the data for the student that authorized the client application is not implemented
* The API currently is very limited and only supports accessing student profile
* There is elaborate help section (analogue to http://developer.yammer.com/api/oauth2.html) 
