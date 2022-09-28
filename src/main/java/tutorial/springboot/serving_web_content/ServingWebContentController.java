package tutorial.springboot.serving_web_content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This controller is similar to RestfulController (we can watch in it for explanations about spring's services controllers). The main
 * difference is in this case :<br/>
 * - Beware annotation : it is annotated as @Controller, not @RestController like for Restful controllers.<br/>
 * - the application will respond with a web page that displays HTML. The body of the HTML will contain the answer from this controller.<br/>
 *
 */
@Controller
public class ServingWebContentController {

	/**
	 * In Spring’s approach to building web sites, HTTP requests will be handled by our controllers. Here,
	 * it will return the name of a View, that is responsible for rendering the HTML content. Spring will
	 * found our view in the path of the project ("src/main/resources/templates/serving_web_content/greeting.html" 
	 * for this application) and display it.<br/>
	 * <br/>
	 * To be precise : the implementation of the method body relies on a view technology (in this case, Thymeleaf that is
	 * declared as a dependency in pom.xml) to perform server-side rendering of the HTML. Watch inside
	 * "src/main/resources/templates/serving_web_content/greeting.html" file for more details.
	 */
	@GetMapping("/web_greeting")
	public String webGreeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		// Here we return the name "path" of the template above the "src/main/resourcestemplates" folder. 
		// "serving_web_content/greeting" correspond to "serving_web_content/greeting.html" file.
		return "serving_web_content/greeting";
	}
	
	
	
}
