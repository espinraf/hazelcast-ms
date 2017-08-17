package se.voipbusiness.ms;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

/**
 * Created by espinraf on 2017-07-19.
 */
@Controller
@ManagedResource(objectName = "jmxSLL:name=TestJmxController")
public class TestWebController {

    private final Logger log = LoggerFactory.getLogger(TestWebController.class);

    @Autowired
    protected TestWebSocketHandler webSocketHandler;

    @RequestMapping(value = "/wwwroot", method = RequestMethod.GET)
    public String index() {
        log.info("Serving Static Pages....");
        webSocketHandler.sendToAll("Greetings from Web controller");

        ContentResponse response = null;

        try {
            HttpClient httpc = new HttpClient();
            httpc.start();
            response = httpc.GET("http://11.1.0.3:9090/caching/2");
            webSocketHandler.sendToAll(response.getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "controller.html";
    }

    @ManagedOperation
    public String jmxDemo(){
        System.out.println("Hola Jörgen !!!!!!  ");
        String msg = "<b>Funciona Chingon</b>";
        return msg;
    }
}
