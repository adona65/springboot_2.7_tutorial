package tutorial.springboot.rest_application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ControllerAdvice indicate a type of @Component for classes that declare @ExceptionHandler, @InitBinder, or @ModelAttribute methods 
 * to be shared across multiple @Controller classes.<br/>
 * Here, we will handle exceptions. See method below.
 */
@ControllerAdvice
public class EmployeeNotFoundAdvice {

	/**
	 * This method will handle EmployeeNotFoundExceptions, by intercepting the exception from controllers then executing whatever 
	 * we want. Here, it will just return the exception's message.<br/>
	 * - @ResponseBody indicate that the method return value should be bound to the web response body.<br/>
	 * - @ExceptionHandler indicate this method will handle exceptions, and specify which type of exceptions.<br/>
	 * - @ResponseStatus indicate which status code that should be returned.<br/>
	 * <br/>
	 * Whitout this proper EmployeeNotFoundException handling, if such an exception happened, spring would display a "Whitelabel Error Page"
	 * containig "This application has no explicit mapping for /error, so you are seeing this as a fallback." message, and the trace of
	 * the error.
	 */
	@ResponseBody
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String employeeNotFoundHandler(EmployeeNotFoundException ex) {
		return ex.getMessage();
	}
}