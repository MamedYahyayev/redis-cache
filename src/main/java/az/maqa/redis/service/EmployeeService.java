package az.maqa.redis.service;

import java.util.List;

import az.maqa.redis.dto.EmployeeDTO;
import az.maqa.redis.model.Employee;
import az.maqa.redis.request.RequestEmployee;
import az.maqa.redis.response.ResponseStatus;

public interface EmployeeService {

	List<EmployeeDTO> getAllEmployees();

	EmployeeDTO getEmployeeById(Long id);

	EmployeeDTO saveEmployee(RequestEmployee requestEmployee);

	EmployeeDTO updateEmployee(RequestEmployee requestEmployee, Long id);

	ResponseStatus deleteEmployee(Long id);

	

}
