package tutorial.springboot.rest_application;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {

  EmployeeNotFoundException(Long id) {
    super("Could not find employee " + id);
  }
}
