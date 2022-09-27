package tutorial.springboot.basics;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class can host beans that will be retrieved by Sprint Boot. This is allowed by @Configuration annotation. Whitout it,
 * Spring Boot cannot find beans declared in this class.
 */
@Configuration
public class BeansDisplayer {

	/**
	 * We declare a bean simply using @Bean annotation.<br/>
	 * <br/>
	 * This CommandLineRunner beans retrieves all the beans that were created by the application or that were automatically added 
	 * by Spring Boot, sorts them then print this list in command prompt in which we have used "mvnw spring-boot:run" to run the
	 * application.<br/>
	 * <br/>
	 * Please not that this list will be printed after all Spring's starting messages.
	 */
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's bean from BeansDisplayer class display all the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}
}
