package br.com.javaweb.webjwt.dtos;

import br.com.javaweb.webjwt.models.EmployeeModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private String description;
    private String sku;
    private Integer employeeId;
}
