# MasterCard_Code_test

### How to run?

Run the MastercardApplication class as a Java Application.

Tomcat port number is 8081 given in application.properties

### This application provides the below endpoints
http://localhost:8081/connected

http://localhost:8081/connected?origin=Boston&destination=Philadelphia
Returns Yes because the path between the origin and destination is provided in connected_cities.txt file under resources folder

### Testcases added to the application.
testValidInterconnect
testValidDirectConnect
testValidSameCity
testInvalidConnect

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

