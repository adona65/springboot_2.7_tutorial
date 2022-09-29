package tutorial.springboot.rest_application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Convenience class that will load some data into memory-embedded H2 data base . This behavior is used for working with this
 * tutorial without having to implement a "real" data base or store data on disk.
 */
@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  /**
   * Another CommandLineRunner bean is declared into BeansDisplayer class. Spring Boot will run ALL CommandLineRunner beans once 
   * the application context is loaded.<br/>
   * <br/>
   * Spring will automatically inject an EmployeeRepository when method will be called, then this runner will create two entities 
   * and store them into the data base.<br/>
   * Same things is performer with OrderRepository.
   */
  @Bean
  CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {

    return args -> {
    	log.info("Preloading " + employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar")));
    	log.info("Preloading " + employeeRepository.save(new Employee("Frodo", "Baggins", "thief")));
    	
    	employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));
    	
    	orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
        orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

        orderRepository.findAll().forEach(order -> {
          log.info("Preloaded " + order);
        });
    };
  }
}