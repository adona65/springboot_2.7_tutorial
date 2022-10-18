package tutorial.springboot.sqlite;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {

	@Override
	public EntityModel<Person> toModel(Person person) {
		return EntityModel.of(person, //
					linkTo(methodOn(SqliteController.class).one(person.getId())).withSelfRel(),
					linkTo(methodOn(SqliteController.class).all()).withRel("employees"));
	}
}