package az.maqa.redis.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.maqa.redis.model.Address;
import az.maqa.redis.model.Person;
import az.maqa.redis.repository.PersonRepository;

@RestController
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam String firstName, @RequestParam String lastName) {
        if(firstName == null) {
            return ResponseEntity.badRequest().build();
        }
        Person person = Person.builder()
                .firstname(firstName)
                .lastname(lastName)
                .address(Address.builder()
                        .street("street")
                        .city("city").build()
                ).build();
        Person savedPerson = personRepository.save(person);
        return ResponseEntity.ok(savedPerson.getId());
    }

	@GetMapping(value = "/persons")
	public ResponseEntity<Iterable<Person>> findAllPerson() {
		Iterable<Person> persons = personRepository.findAll();
		return ResponseEntity.ok(persons);
	}
	
	@GetMapping("/personsByFirstName/{firstname}")
    public ResponseEntity<Iterable<Person>> personsByFirstName(@PathVariable String firstname) {
        List<Person> personList = personRepository.findByFirstname(firstname);
        return ResponseEntity.ok(personList);
    }

	@GetMapping("/personsById/{id}")
    public ResponseEntity<Optional<Person>> personsById(@PathVariable String id) {
        Optional<Person> personList = personRepository.findById(id);
        return ResponseEntity.ok(personList);
    }
	
}
