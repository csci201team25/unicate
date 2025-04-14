package csci201team25.unicate_server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/*** Determines what webpage is returned when a URL on the server is accessed ***/
@Controller
public class MainController {
    @RequestMapping(value="/hello-world", method=RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "<h3>Hello world!</h3>";
    }

    @RequestMapping("/")
    public String homePage() {
    	return "Home";
    }

    @RequestMapping(value="/AddSchool.html", method=RequestMethod.GET)
    public String addSchoolPage() {
    	return "AddSchool";
    }

    @RequestMapping(value="/Login.html", method=RequestMethod.GET)
    public String loginPage() {
    	return "login";
    }
}
