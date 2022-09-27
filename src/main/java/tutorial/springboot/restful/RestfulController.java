package tutorial.springboot.restful;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Here we created a controller for a Restful application. A key difference between a traditional MVC controller and this RESTful web service 
 * controller is the way that the HTTP response body is created. Rather than relying on a view technology to perform server-side rendering 
 * of the greeting data to HTML, this RESTful web service controller populates and returns a Greeting object. The object data will be written 
 * directly to the HTTP response as JSON.<br/>
 * <br/>
 * Here, @RestController annotation  marks the class as a controller where every method returns a domain object instead of a view. 
 * It is shorthand for including both @Controller and @ResponseBody.
 */
@RestController
public class RestfulController {

	/**
	 * @Autowired marks this field as to be autowired by Spring's dependency injection facilities. When a "RestfulController"
	 * object will be created, Spring will see it required an "AtomicLong" object. Spring will look into it's context for a bean of this 
	 * type, found that RestfulBeansProvider.getAtomicLong() provide it (thank to the various annotations), and inject it into the
	 * newly instanced RestfulController object.<br/>
	 * <br/>
	 * Beware : if we annotate a variable with @Autowired, but no bean provider is found for the corresponding type by Spring in its 
	 * context, a build failure will be launch at application start up.
	 */
	@Autowired
	private AtomicLong atomicLong;
	
	/**
	 * Service that will :<br/>
	 * - handle GET requests for /greeting<br/>
	 * - accept an optionnal name parameter in the query string<br/>
	 * - should return a 200 OK response<br/>
	 * - add JSON in response's body<br/>
	 * <br/>
	 * We will note that we used here @GetMapping for handling Get requests, but it exists companion annotations for other HTTP verbs 
	 * ( @PostMapping for POST and so on).<br/>
	 * <br/>
	 * For having a correct response, the Greeting object must be converted to JSON. Thanks to Spring’s HTTP message converter support, 
	 * we need not do this conversion manually. Because Jackson 2 is on the classpath (it is included by default by the web starter),
	 *  Spring’s MappingJackson2HttpMessageConverter is automatically chosen to convert the Greeting instance to JSON.
	 */
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(atomicLong.incrementAndGet(), String.format("Hello, %s!", name));
	}
}
