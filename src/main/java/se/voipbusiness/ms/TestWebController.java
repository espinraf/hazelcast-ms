package se.voipbusiness.ms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by espinraf on 2017-07-19.
 */
@Controller
public class TestWebController {

    private final Logger log = LoggerFactory.getLogger(TestWebController.class);

    @Autowired
    protected TestWebSocketHandler webSocketHandler;

    @RequestMapping(value = "/wwwroot", method = RequestMethod.GET)
    public String index() {
        log.info("Serving Static Pages....");
        webSocketHandler.sendToAll("Greetings from Web controller");
        return "controller.html";
    }
}
