package br.com.javaweb.webjwt.dtos;

import br.com.javaweb.webjwt.department.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {

    private String firstName;
    private String lastName;
    private Department department;
}
