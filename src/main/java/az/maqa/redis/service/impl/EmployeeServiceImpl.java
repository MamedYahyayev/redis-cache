package az.maqa.redis.service.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import az.maqa.redis.dto.EmployeeDTO;
import az.maqa.redis.model.Employee;
import az.maqa.redis.repository.EmployeeRepository;
import az.maqa.redis.request.RequestEmployee;
import az.maqa.redis.response.ResponseStatus;
import az.maqa.redis.service.EmployeeService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	CacheManager cacheManager;

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

	@Override
	public EmployeeDTO updateEmployee(RequestEmployee requestEmployee, Long id) {
		ModelMapper modelMapper = new ModelMapper();

		Employee employee = modelMapper.map(requestEmployee, Employee.class);
		employee.setId(id);

		Employee updatedEmployee = employeeRepository.save(employee);

		EmployeeDTO returnValue = modelMapper.map(updatedEmployee, EmployeeDTO.class);

		return returnValue;
	}

	@CacheEvict(value = { "getAllEmployees", "getEmployee" })
	public void deleteCache() {
		cacheManager.getCacheNames().stream().forEach(cache -> cacheManager.getCache(cache).clear());
		log.info("Caches Deleted");
	}

	// Delete Cache With Scheduler
	@Scheduled(fixedRate = 10000)
	public void deleteCacheAtInterval() {
		deleteCache();
	}

	@Override
	public ResponseStatus deleteEmployee(Long id) {
		ResponseStatus response = new ResponseStatus();
		employeeRepository.deleteById(id);

		Employee employee = employeeRepository.findEmployeeById(id);

		if (employee == null) {
			response.setCode(101);
			response.setMessage("Employee deleted...");
		}

		return response;
	}

}
