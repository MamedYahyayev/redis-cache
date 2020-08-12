package az.maqa.redis.service;

import java.util.List;

import az.maqa.redis.dto.EmployeeDTO;
import az.maqa.redis.request.RequestEmployee;

public interface EmployeeService {

	List<EmployeeDTO> getAllEmployees();

	EmployeeDTO getEmployeeById(Long id);

	EmployeeDTO saveEmployee(RequestEmployee requestEmployee);

}
