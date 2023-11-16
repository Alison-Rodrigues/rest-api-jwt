package br.com.javaweb.webjwt.services;

import br.com.javaweb.webjwt.dtos.EmployeeDto;
import br.com.javaweb.webjwt.models.EmployeeModel;
import br.com.javaweb.webjwt.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeModel> listAll() {
        return employeeRepository.findAll();
    }

    public Optional<EmployeeModel> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    public void save(EmployeeDto employeeDto) {

        EmployeeModel employee = new EmployeeModel();

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));

        employeeRepository.save(employee);
    }

    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }


}
