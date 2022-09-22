package tutorial.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a rest controller class that allow containing methods that may be joined as rest services (see methods documentation
 * for more details). This ability is declared using @RestController annotation, that combined two others :<br/>
 * - @Controller that indicates the annotated class is a "Controller" (e.g. a web controller).<br/>
 * - @ResponseBody that indicates a method return value should be bound to the web response body.<br/>
 * ==> This two annotations results in web requests returning data rather than a view.<br/>
 * <br/>
 * Types that carry this @RestController annotation are treated as controllers where @RequestMapping methods assume @ResponseBody 
 * semantics by default. 
 */
@RestController
public class HelloWorldController {

	/**
	 * Using @GetMapping annotation, we declare this method as a HTTP GET requests handler. This because @GetMapping is a composed 
	 * annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET).<br/>
	 * <br/>
	 * It declare that all get request made on "{application url}/hello" ("http://localhost:8080/hello" by default) will be mapped to this
	 * method<br/>
	 * <br/>
	 * @RequestParam indicate that we may expect a "name" parameter in the request, and using "World" as a default value if the parameter is
	 * absent. Thus lead to :<br/>
	 * - Getting "Hello World!" answer if we call "http://localhost:8080/hello".<br/>
	 * - Getting "Hello Amy!" answer if we call "http://localhost:8080/hello?name=Amy".
	 */
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	
	/**
	 * As for previous explanations, this method will answer to "http://localhost:8080/bye" get requests by :<br/>
	 * - Responding "Goodby World!" to "http://localhost:8080/bye" call.<br/>
	 * - Responding "Goodby Amy!" to "http://localhost:8080/bye?name=Amy" call.
	 */
	@GetMapping("/bye")
	public String bye(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Goodbye %s!", name);
	}

	/**
	 * This allow to handle requests to application's default address. It will answer "Welcome to default page!" 
	 * to calls on "http://localhost:8080".
	 */
	@GetMapping("/")
	public String index() {
		return "Welcome to default page!";
	}
}
