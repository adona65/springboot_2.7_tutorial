package tutorial.springboot.rest_application;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * This class implements RepresentationModelAssembler interface, which is part of Spring HATEOAS. It has one method, toModel(), 
 * whose based on converting a non-model object (here Employee) into a model-based object (here EntityModel<Employee>).<br/>
 * <br/>
 * In addition to implementing the interface, we just had to add @Component annotation for allowing this assembler to be automatically 
 * created by Spring when the application starts. @Component annotated classes are considered as candidates for auto-detection when 
 * using annotation-based configuration and classpath scanning. 
 */
@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

	/**
	 * Convert Employee objects to EntityModel<Employee> objects. 
	 */
	@Override
	public EntityModel<Employee> toModel(Employee employee) {
		return EntityModel.of(employee, //
					linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
					linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
	}
}