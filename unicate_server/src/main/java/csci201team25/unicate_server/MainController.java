package csci201team25.unicate_server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


// determines what webpage is returned when a URL on the server is accessed
@Controller
public class MainController {
    @RequestMapping(value="/hello-world", method=RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "<h3>Hello world!</h3>";
    }

    @RequestMapping({"/", "home.html"})
    public String homePage() {
    	return "Home";
    }

    @RequestMapping(value="/add-university.html", method=RequestMethod.GET)
    public String addSchoolPage() {
    	return "add-university";
    }

    @RequestMapping(value={"/Login.html", "/login.html"}, method=RequestMethod.GET)
    public String loginPage() {
    	return "login";
    }

    @GetMapping(value={"/register.html", "/Register.html"})
    public String registerPage() { return "register"; }

    @GetMapping(value="/calendar.html")
    public String calendarPage() {
        return "calendar";
    }

    @GetMapping(value="/post.html")
    public String postPage() {
        return "post";
    }

    @GetMapping(value="/activities.html")
    public String activitiesPage() {
        return "activities";
    }
}
