
package com.hikingtrails;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import com.hikingtrails.domain.HikingTrail;
import com.hikingtrails.domain.HikingTrailsRepository;

/**
 *
 * @author John
 */

@Configuration
public class InitializeDatabase {
    
    public static final Logger logger = LoggerFactory.getLogger(InitializeDatabase.class);    

    // Initialize the database and run some tests using the repository interface.
    @Bean
    public CommandLineRunner initDatabase(HikingTrailsRepository repository) {
        return (args) -> {
            
            // Save a few hiking trails
            repository.save(new HikingTrail("Middle Fork", 5.0f, "easy"));
            repository.save(new HikingTrail("Long Valley", 12.0f, "moderate"));

            // Get all of the saved hiking trails
            logger.info("Hiking trails found with findAll():");
            logger.info("-------------------------------");
            for (HikingTrail hikingTrail : repository.findAll()) {
              logger.info(hikingTrail.toString());
            }
            logger.info("");

            // Get a hiking trail by Id
            Optional<HikingTrail> hikingTrail = repository.findById(1L);
            logger.info("Hiking trail found with findById(1L):");
            logger.info("--------------------------------");
            if (hikingTrail.isPresent()) {
                logger.info(hikingTrail.get().toString());
            }
            logger.info("");

            // Get a hiking trail by name
            logger.info("Hiking trail found with findByName('Middle Fork'):");
            logger.info("--------------------------------------------");
            HikingTrail hikingTrail1 = repository.findByName("Middle Fork");
            if (hikingTrail1.getName().equals("One")) {
                logger.info(hikingTrail1.toString()); 
            }

            logger.info("");
        };
    }
        
}
