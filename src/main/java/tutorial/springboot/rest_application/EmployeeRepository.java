package tutorial.springboot.rest_application;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring makes accessing data easy. By simply declaring this interface we automatically will be able to work with Data Base. We
 * will for example may :<br/>
 * - Create new Employees.<br/>
 * - Update existing ones.<br/>
 * - Delete Employees.<br/>
 * - Find Employees (one, all, or search by simple or complex properties).<br/>
 * <br/>
 * All of it is allowed because our interface extends Spring Data JPA’s JpaRepository, specifying the domain type as Employee and *
 * the id type as Long (which  correspond to the type of "id" field, annotated as primary key in Employee class).
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
