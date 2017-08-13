package se.voipbusiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
//@ComponentScan(basePackages={"se.voipbusiness.batch", "se.voipbusiness.ms"})
public class DemoApp {

    public static void main(String[] args) {

        System.out.println(args);
        SpringApplication.run(DemoApp.class, args);

    }
}
