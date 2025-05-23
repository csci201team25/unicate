package csci201team25.unicate_server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ActivityController {
	// basically an API that lets you get the activities and the universities

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping("/activities")
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @GetMapping("/activities/by-university")
    public List<Activity> getActivitiesByUniversity(@RequestParam String universityName) {
        return activityRepository.findByUniversity_UniversityName(universityName);
    }
    
    @GetMapping("/universities/")
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }
}
