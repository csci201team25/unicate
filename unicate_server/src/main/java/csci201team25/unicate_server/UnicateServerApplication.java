package csci201team25.unicate_server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UnicateServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnicateServerApplication.class, args);
	}
	
    @Bean
    CommandLineRunner printUniversityNames(UniversityRepository uniRepo, ActivityRepository actRepo) {
        return args -> {
            System.out.println("== University table contents on Startup ==");
            uniRepo.findAll().forEach(System.out::println);
            System.out.println("== Find university by name test USC ==");
            System.out.println(uniRepo.findByUniversityName("University of Southern California"));
            System.out.println("== Find Activities by name test USC ==");
            System.out.println(actRepo.findByUniversity_UniversityName("University of Southern California"));
        };
    }
}
