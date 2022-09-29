package tutorial.springboot.rest_application;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Each new concept in this controller (compared to tutorials seen previously in classes from other packages) is 
 * described directly in doc of or comments inside corresponding methods.<br/>
 * <br/>
 * In this controller, we want build HATEOAS resources that will allow use of links. For this :<br/>
 * - Some methods return EntityModel<Employee> instead of just Employee. EntityModel<T> is a generic container from Spring HATEOAS 
 * 		that includes not only the data but a collection of links.<br/>
 * - Calls like "linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel()" asks that Spring HATEOAS build a link to 
 * 		EmployeeController.one() method, and flag it as a self link (a self link is a link to the page on which the link appears).<br/>
 * - Calls like "linkTo(methodOn(EmployeeController.class).all()).withRel("employees")" asks Spring HATEOAS to build a link to the 
 * 		aggregate root, all(), and call it "employees".<br/>
 * ==> An example of the return format of this behaviors is available in one() method's javadoc.<br/>
 * - There is also returns of CollectionModel<EntityModel<Employee>>. This is not just a "Collection of employees", but a collection of 
 * 		employee resources. Watch all() method's javadoc for a format example.<br/>
 * <br/>
 * All this possibilities are handled by EmployeeModelAssembler.toModel() method, called many times in this controller.
 */
@RestController
public class EmployeeController {

  private final EmployeeRepository repository;
  private final EmployeeModelAssembler assembler;

  /**
   * Here, "assembler" will be automatically injected by Spring because EmployeeModelAssembler is @Component annotated.
   */
  EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  /**
   * Here an example of the format of what this method will return using Spring HATEOAS capabilities :
   * 
	    {
		  "_embedded": {
		    "employeeList": [
		      {
		        "id": 1,
				"firstName": "Bilbo",
				"lastName": "Baggins",
				"role": "burglar",
				"name": "Bilbo Baggins",
		        "_links": {
		          "self": {
		            "href": "http://localhost:8080/employees/1"
		          },
		          "employees": {
		            "href": "http://localhost:8080/employees"
		          }
		        }
		      },
		      {
		        "id": 2,
				"firstName": "Frodo",
				"lastName": "Baggins",
				"role": "thief",
				"name": "Frodo Baggins",
		        "_links": {
		          "self": {
		            "href": "http://localhost:8080/employees/2"
		          },
		          "employees": {
		            "href": "http://localhost:8080/employees"
		          }
		        }
		      }
		    ]
		  },
		  "_links": {
		    "self": {
		      "href": "http://localhost:8080/employees"
		    }
		  }
		}
   * 
   */
  @GetMapping("/employees")
  CollectionModel<EntityModel<Employee>> all() {
	  // Retrieve all employees from the repository (H2 data base table).
	  List<EntityModel<Employee>> employees = repository.findAll()
			  											.stream()
													    .map(assembler::toModel)
													    .collect(Collectors.toList());
	
	  return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
  }

  /**
   * Described previously but not used since : @PutMapping declare this method as a HTTP POST requests handler.<br/>
   * <br/>
   * Here Spring MVC’s ResponseEntity is used to create an HTTP 201 Created status message. This type of response typically includes 
   * a Location response header, and we use the URI derived from the model’s self-related link.<br/>
   * <br/>
   * The response of calling this service will be as follow :<br/>
   * - Will content some headers :<br/>
   * 
		Location: http://localhost:8080/employees/3
		Content-Type: application/hal+json;charset=UTF-8
		Transfer-Encoding: chunked
		Date: Fri, 10 Aug 2018 19:44:43 GMT
   *<br/>
   * - And a body :<br/>
   * 
	   {
		    "id": 3,
		    "firstName": "Samwise",
		    "lastName": "Gamgee",
		    "role": "gardener",
		    "name": "Samwise Gamgee",
		    "_links": {
		        "self": {
		            "href": "http://localhost:8080/employees/4"
		        },
		        "employees": {
		            "href": "http://localhost:8080/employees"
		        }
		    }
		}
   *
   */
  @PostMapping("/employees")
  ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {
	  // Save the provided employee into the repository (H2 data base table).
	  EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));
	  
	  // Return the model-based version of the saved object.
	  return ResponseEntity
		      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
		      .body(entityModel);
  }

  /**
   * Here an example of the format of what this method will return using Spring HATEOAS capabilities :
   *
	   {
			"id": 1,
			"firstName": "Bilbo",
			"lastName": "Baggins",
			"role": "burglar",
			"name": "Bilbo Baggins",
			"_links": {
				"self": {
					"href": "http://localhost:8080/employees/1"
				},
				"employees": {
					"href": "http://localhost:8080/employees"
				}
			}
		}
   *	
   */
  @GetMapping("/employees/{id}")
  EntityModel<Employee> one(@PathVariable Long id) {
	  // Look for the employee corresponding to provided id into the repository (H2 data base table).
	  Employee employee = repository.findById(id)
		      						.orElseThrow(() -> new EmployeeNotFoundException(id));
	
	  return assembler.toModel(employee);
  }

  /**
   * Described previously but not used since : @PutMapping declare this method as a HTTP PUT requests handler.
   */
  @PutMapping("/employees/{id}")
  ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
	  // Look for the employee corresponding to provided id into the repository (H2 data base table). 
	  // If present, replace it into the repo by the new one.
	  // If absent, create a new employee into the repo.
	  Employee updatedEmployee = repository.findById(id)
									      .map(employee -> {
									        employee.setName(newEmployee.getName());
									        employee.setRole(newEmployee.getRole());
									        return repository.save(employee);
									      })
									      .orElseGet(() -> {
									        newEmployee.setId(id);
									        return repository.save(newEmployee);
									      });
	  
	  EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);
	  
	  return ResponseEntity
		      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
		      .body(entityModel);
  }

  /**
   * Described previously but not used since : @PutMapping declare this method as a HTTP DELETE requests handler.
   */
  @DeleteMapping("/employees/{id}")
  ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
	  // Remove the employee corresponding to provided id from the repository (H2 data base table). 
	  repository.deleteById(id);
	  
	  return ResponseEntity.noContent().build();
  }
}
