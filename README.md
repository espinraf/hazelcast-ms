# Demo Gradle Spring Boot , Batch, Rest, Websockets, WebFilter, Static pages and Hazelcast IMap (Distributed Map)


#### Hazelcast code is from http://hazelcast.org , Microservice example
#### Batch from http://www.kswaughs.com/2016/03/spring-boot-batch-job-scheduler-example.html
#### Websocket https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-websocket-jetty

## Purpose, "how to"

+ Gradle dependencies, not always documentation show the dependencies to the libraries or some times it does not work. (build.gradle)
+ Hazelcast distributed Map (IMap) and to demo the IMap uses a Rest webservice. (TestHazelRest.java)
+ Use of spring-batch, read a csv file and convert to other format. (se.voipbusiness.batch package)
+ Use static web pages or files, spring delivers by default web pages or files  from:

private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
		"classpath:/META-INF/resources/", "classpath:/resources/",
		"classpath:/static/", "classpath:/public/" };

+ Define a web controller. (TestWebController.java)
+ Implement a Websockets server, and how to use it from a web page, resources/static/console.html . (TestWebSocketServerConfiguration and TestWebSocketHandler.java)
+ Use of a web filter (TestFilter.java)
+ In build.gradle there are dependencies to add monitoring using Zipkin, see below how to start Zipkin if it desired to check it out.


## Compile
gradle clean build

## Run

### Alt 1
cd build/distributions

unzip hazelcast-ms-1.0.0.zip

cd hazelcast-ms/bin

./hazelcast-ms

### Alt 2

cd build/libs

java -jar hazelcast-ms-1.0.0.jar

```
.............
.............
2017-07-20 14:40:42.206  INFO 1982 --- [           main] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2017-07-20 14:40:42.217  INFO 1982 --- [           main] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 11 ms
2017-07-20 14:40:42.231  INFO 1982 --- [           main] o.e.jetty.server.AbstractConnector       : Started ServerConnector@9a6fbc0{HTTP/1.1,[http/1.1]}{0.0.0.0:8080}
2017-07-20 14:40:42.232  INFO 1982 --- [           main] .s.b.c.e.j.JettyEmbeddedServletContainer : Jetty started on port(s) 8080 (http/1.1)
2017-07-20 14:40:42.237  INFO 1982 --- [           main] se.voipbusiness.DemoApp                  : Started DemoApp in 14.727 seconds (JVM running for 15.12)
```

## curl commands

### Add some data to the IMAP
curl -X POST http://localhost:9090/caching/1 -d "value=SOMBRERO"

curl -X POST http://localhost:9090/caching/2 -d "value=LOCO"

### Retreive some data
curl http://localhost:8080/caching/1

## URLs

Default static page http://localhost:9090

Use WebController http://localhost:9090/wwwroot

Call information endpoint  http://localhost:9090/metrics, check when the app is started you will see all endpoints

Static page which use websockets http://localhost:9090/console.html, if the connection is up,
then if you surf to http://localhost:9090/wwwroot it will send a message to the console web page.


## To run Zipkin with Docker
docker run --rm -it -p 9411:9411 openzipkin/zipkin

Surf to http://localhost:9411