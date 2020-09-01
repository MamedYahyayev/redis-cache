package az.maqa.redis.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import az.maqa.redis.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

	List<Person> findByFirstname(String firstName);

}
