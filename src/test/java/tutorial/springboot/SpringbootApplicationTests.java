package tutorial.springboot;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Using correct annotations, Spring allow us to write unit tests on our application :<br/>
 * - @SpringBootTest indicate a test class that runs Spring Boot based tests. This annotation ask for the whole application context to 
 * 		be created. An alternative would be to ask Spring Boot to create only the web layers of the context by using @WebMvcTest<br/>
 * - @AutoConfigureMockMvc allow to inject a MockMvc instance. MockMvc comes from Spring Test and lets, through a set of convenient 
 * 		builder classes, send HTTP requests into the DispatcherServlet and make assertions about the result.
 */
@SpringBootTest
@AutoConfigureMockMvc
class SpringbootApplicationTests {

	/**
	 * @Autowired marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection 
	 * facilities. This MockMvc object will be injected thanks to @AutoConfigureMockMvc annotations put on the class.<br/>
	 * In this method, we mock the HTTP request cycle.
	 */
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Welcome to default page!")));
	}
}
