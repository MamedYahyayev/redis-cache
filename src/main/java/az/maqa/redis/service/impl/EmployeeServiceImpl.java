package az.maqa.redis.service.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import az.maqa.redis.dto.EmployeeDTO;
import az.maqa.redis.model.Employee;
import az.maqa.redis.repository.EmployeeRepository;
import az.maqa.redis.request.RequestEmployee;
import az.maqa.redis.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	@Cacheable(cacheNames = "getAllEmployees")
	public List<EmployeeDTO> getAllEmployees() {
		ModelMapper modelMapper = new ModelMapper();

		List<Employee> employees = employeeRepository.findAllByActive(1);

		Type listType = new TypeToken<List<EmployeeDTO>>() {
		}.getType();

		List<EmployeeDTO> returnValue = modelMapper.map(employees, listType);

		return returnValue;
	}

	@Override
	@Cacheable(cacheNames = "getEmployee")
	public EmployeeDTO getEmployeeById(Long id) {
		ModelMapper modelMapper = new ModelMapper();

		Employee employee = employeeRepository.findById(id).get();

		EmployeeDTO returnValue = modelMapper.map(employee, EmployeeDTO.class);

		return returnValue;
	}

	@Override
	public EmployeeDTO saveEmployee(RequestEmployee requestEmployee) {
		ModelMapper modelMapper = new ModelMapper();

		Employee employee = modelMapper.map(requestEmployee, Employee.class);

		Employee savedEmployee = employeeRepository.save(employee);

		EmployeeDTO returnValue = modelMapper.map(savedEmployee, EmployeeDTO.class);
		
		return returnValue;
	}

	@CacheEvict(cacheNames = "getAllEmployees")
	public  void deleteCache() {
		System.out.println("Cache deleted");
	}
	
}
