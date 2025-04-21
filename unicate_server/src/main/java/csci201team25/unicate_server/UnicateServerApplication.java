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
    CommandLineRunner printUniversityNames(UniversityRepository universityRepository) {
        return args -> {
            System.out.println("== University table contents on Startup ==");
            universityRepository.findAll().forEach(System.out::println);
            System.out.println("== Find university by name test USC ==");
            System.out.println(universityRepository.findByUniversityName("University of Southern California"));
        };
    }
}
