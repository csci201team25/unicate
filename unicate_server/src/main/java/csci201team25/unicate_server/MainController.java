package csci201team25.unicate_server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/*** Determines what webpage is returned when a URL on the server is accessed ***/
@Controller
public class MainController {
    @RequestMapping(value="/", method=RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "<h3>Hello world!</h3>";
    }
    
}
