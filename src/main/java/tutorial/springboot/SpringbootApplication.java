package tutorial.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the "main" Spring Boot's class, that launch the application and all Spring's process.<br/>
 * <br/>
 * It is detected by Spring thanks to @SpringBootApplication annotation. As stated in Spring's documentation, this annotation :<br/>
 * - indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate 
 * 		bean definitions and service requests for those beans at runtime.<br/>
 * - triggers auto-configuration that enable auto-configuration of the Spring Application Context. This tells Spring Boot to start 
 * 		adding beans based on classpath settings, other beans, and various property settings. For example, if spring-webmvc is on the 
 * 		classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a 
 * 		DispatcherServlet.<br/>
 * - triggers component scanning that provides support parallel with Spring XML's <context:component-scan> element. It tells Spring to look 
 * 		for other components, configurations, and services in the tutorial.springboot package, letting it find the controllers. For example,$
 * 		if "HelloWorldController.java" controller would be placed in another package, let say "fake.package", it wouldn't be found by
 * 		Sprint Boot, and corresponding requests wouldn't be handled by this controller.<br/>
 *<br/>
 * As a summary, @SpringBootApplication is a convenience annotation that is equivalent to declaring @Configuration, 
 * @EnableAutoConfiguration and @ComponentScan.<br/>
 */
@SpringBootApplication
public class SpringbootApplication {

	/**
	 * The main() method uses Spring Boot’s SpringApplication.run() to launch the application. Unlike some old Spring applications,
	 * there is not a single line of XML, nor web.xml file.<br/>
	 * This allow to work with pure Java and we did not have to deal with configuring any plumbing or infrastructure.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
