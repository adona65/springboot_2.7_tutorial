package tutorial.springboot.sqlite;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SqliteController {
	
	private final PersonRepository repository;
	private final PersonModelAssembler assembler;
	
	SqliteController(PersonRepository repository, PersonModelAssembler assembler) {
		    this.repository = repository;
		    this.assembler = assembler;
	}
	
    @GetMapping("/sqlite_persons")
    CollectionModel<EntityModel<Person>> all() {
	    List<EntityModel<Person>> persons = repository.findAll()
			  										  .stream()
													  .map(assembler::toModel)
													  .collect(Collectors.toList());

	    return CollectionModel.of(persons, linkTo(methodOn(SqliteController.class).all()).withSelfRel());
    }
    
    @PostMapping("/sqlite_persons")
    ResponseEntity<?> newPerson(@RequestBody Person newPerson) {
  	  EntityModel<Person> entityModel = assembler.toModel(repository.save(newPerson));
  	  
  	  return ResponseEntity
  		      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
  		      .body(entityModel);
    }
    
	@GetMapping("/sqlite_person/{id}")
	EntityModel<Person> one(@PathVariable Long id) {
		Person person = repository.findById(id)
								.orElseThrow(() -> new PersonNotFoundException(id));
		
		return assembler.toModel(person);
	}
	
    @DeleteMapping("/sqlite_person/{id}")
    ResponseEntity<?> deletePerson(@PathVariable Long id) {
	    repository.deleteById(id);
	  
	    return ResponseEntity.noContent().build();
    }
}
