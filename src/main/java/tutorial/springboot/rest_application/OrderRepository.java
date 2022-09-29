package tutorial.springboot.rest_application;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Allow working with orders in data base. Watch EmployeeRepository for more explanations.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}
