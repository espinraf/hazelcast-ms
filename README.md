# Demo Gradle Spring Boot , Batch, Rest, Websockets, WebFilter, Static pages and Hazelcast


### Hazelcast code is from http://hazelcast.org , Microservice example
### Batch from http://www.kswaughs.com/2016/03/spring-boot-batch-job-scheduler-example.html

## Compile
gradle clean build

## Run

cd build/distributions

unzip hazelcast-ms.zip

cd hazelcast-ms/bin

./hazelcast-ms

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

curl -X POST http://localhost:8080/caching/1 -d "value=CHINGON"

curl -X POST http://localhost:8080/caching/2 -d "value=LOCO"

curl http://localhost:8080/caching/1

## URLs

http://localhost:8080/wwwroot

http://localhost:8080/metrics

http://localhost:8080/console.html