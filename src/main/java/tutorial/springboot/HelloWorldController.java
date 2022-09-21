package tutorial.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldController.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	
	@GetMapping("/bye")
	public String bye(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Goodbye %s!", name);
	}
}
