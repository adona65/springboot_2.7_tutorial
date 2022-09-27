package tutorial.springboot.restful;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestfulBeansProvider {

	/**
	 * This annotated method provide to Spring beans of "AtomicLong" type.
	 */
	@Bean
	public AtomicLong getAtomicLong() {
		return new AtomicLong();
	}
}
