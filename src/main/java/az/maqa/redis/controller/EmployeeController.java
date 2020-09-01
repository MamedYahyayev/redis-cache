package az.maqa.redis.controller;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.maqa.redis.dto.EmployeeDTO;
import az.maqa.redis.model.Employee;
import az.maqa.redis.request.RequestEmployee;
import az.maqa.redis.response.ResponseEmployee;
import az.maqa.redis.response.ResponseStatus;
import az.maqa.redis.service.EmployeeService;
import az.maqa.redis.service.impl.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@GetMapping
	public List<ResponseEmployee> getAllEmployees() {
		ModelMapper modelMapper = new ModelMapper();

		List<EmployeeDTO> employees = employeeService.getAllEmployees();

		Type listType = new TypeToken<List<ResponseEmployee>>() {
		}.getType();

		List<ResponseEmployee> response = modelMapper.map(employees, listType);

		return response;
	}

	@GetMapping("/{id}")
	public ResponseEmployee getEmployee(@PathVariable Long id) {
		ModelMapper modelMapper = new ModelMapper();

		EmployeeDTO employee = employeeService.getEmployeeById(id);

		ResponseEmployee response = modelMapper.map(employee, ResponseEmployee.class);

		return response;
	}

	@PostMapping
	public ResponseEmployee addEmployee(@RequestBody RequestEmployee requestEmployee) {
		ModelMapper modelMapper = new ModelMapper();

		EmployeeDTO employee = employeeService.saveEmployee(requestEmployee);

		ResponseEmployee response = modelMapper.map(employee, ResponseEmployee.class);

		employeeServiceImpl.deleteCache();

		return response;
	}

	@PutMapping("/{id}")
	public ResponseEmployee updateEmployee(@RequestBody RequestEmployee requestEmployee, @PathVariable Long id) {
		ModelMapper modelMapper = new ModelMapper();

		EmployeeDTO employee = employeeService.updateEmployee(requestEmployee, id);

		ResponseEmployee response = modelMapper.map(employee, ResponseEmployee.class);

		employeeServiceImpl.deleteCache();

		return response;
	}

	@DeleteMapping("/{id}")
	public ResponseStatus deleteEmployee(@PathVariable Long id) {
		ResponseStatus response = employeeService.deleteEmployee(id);

		employeeServiceImpl.deleteCache();

		return response;
	}

}
