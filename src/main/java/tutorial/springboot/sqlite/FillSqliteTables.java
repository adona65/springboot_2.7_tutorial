package tutorial.springboot.sqlite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Convenience class that will load some data into SQLite database.
 */
@Configuration
public class FillSqliteTables {

  private static final Logger log = LoggerFactory.getLogger(FillSqliteTables.class);

  @Bean
  CommandLineRunner initSqlite(PersonRepository personRepository) {

    return args -> {
    	log.info("Preloading " + personRepository.save(new Person("Andrew", "He likes cakes.")));
    	log.info("Preloading " + personRepository.save(new Person("Garfield", "Meoooow.")));
    	
    	personRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));
    };
  }
}