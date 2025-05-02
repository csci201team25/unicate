package csci201team25.unicate_server;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

// repository for communicating with the uni table in the db
public interface ActivityRepository extends JpaRepository<Activity, Long>{
	List<Activity> findByActName(String activityName);
	List<Activity> findByUniversity_UniversityName(String universityName);
	List<Activity> findAll();
}