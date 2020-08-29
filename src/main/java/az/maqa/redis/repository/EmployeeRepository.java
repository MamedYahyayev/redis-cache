package az.maqa.redis.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import az.maqa.redis.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<Employee> findAllByActive(Integer active);
	
	Employee findEmployeeById(Long id);

}
