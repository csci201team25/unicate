package csci201team25.unicate_server;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

// repo for communicating with the uni table in the DB
public interface UniversityRepository extends JpaRepository<University, Long>{
	List<University> findByUniversityName(String universityName);
	List<University> findAll();
}