package tutorial.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the "main" Spring Boot's class, that launch the application and all Spring's process.<br/>
 * <br/>
 * It is detected by Spring thanks to @SpringBootApplication annotation. As stated in Spring's documentation, this annotation :<br/>
 * - indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate 
 * 		bean definitions and service requests for those beans at runtime.<br/>
 * - triggers auto-configuration that enable auto-configuration of the Spring Application Context.<br/>
 * - triggers component scanning that provides support parallel with Spring XML's <context:component-scan> element. <br/>
 *<br/>
 * As a summary, @SpringBootApplication is a convenience annotation that is equivalent to declaring @Configuration, 
 * @EnableAutoConfiguration and @ComponentScan.<br/>
 */
@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
