package tutorial.springboot.rest_application;

@SuppressWarnings("serial")
public class OrderNotFoundException extends RuntimeException {

  OrderNotFoundException(Long id) {
    super("Could not find order " + id);
  }
}
