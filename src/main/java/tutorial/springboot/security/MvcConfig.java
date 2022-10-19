package tutorial.springboot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class that configures Spring MVC for Spring security tutorial.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	/**
	 * Overrides the method of the same name in WebMvcConfigurer. It adds three view controllers. <br/>
	 * The first view controllers reference the view whose name is home (defined in security/home.html).<br/>
	 * Another references the view named hello (defined in security/hello.html). <br/>
	 * The third view controller references another view named login.<br/>
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/security_home").setViewName("security/security_home");
		registry.addViewController("/security_hello").setViewName("security/security_hello");
		registry.addViewController("/security_login").setViewName("security/security_login");
	}

}