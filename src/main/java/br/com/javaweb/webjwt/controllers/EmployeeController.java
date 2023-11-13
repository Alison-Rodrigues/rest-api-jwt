package br.com.javaweb.webjwt.controllers;

import br.com.javaweb.webjwt.dtos.EmployeeDto;
import br.com.javaweb.webjwt.dtos.ResponseDto;
import br.com.javaweb.webjwt.models.EmployeeModel;
import br.com.javaweb.webjwt.services.EmployeeService;
import br.com.javaweb.webjwt.util.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<?> listAll() {
        List<EmployeeModel> employeeModel = employeeService.listAll();

        if (employeeModel.size() == 0) {
            ResponseDto response = ResponseDto.create();
            response.setStatus(HttpStatus.OK.name());
            response.setMessage(ResponseMessages.MESSAGE_NO_CONTENT);
            response.setData(employeeModel);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        }

        ResponseDto response = ResponseDto.create();
        response.setStatus(HttpStatus.OK.name());
        response.setMessage(ResponseMessages.MESSAGE_SUCCESSFULLY);
        response.setData(employeeModel);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {

        Optional<EmployeeModel> employeeModel = employeeService.findById(id);

        if (employeeModel.isEmpty()) {
            ResponseDto response = ResponseDto.create();
            response.setStatus(HttpStatus.NOT_FOUND.name());
            response.setMessage(String.format(ResponseMessages.MESSAGE_NOT_FOUND, "Employee"));

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(response);
        }

        ResponseDto response = ResponseDto.create();
        response.setStatus(HttpStatus.OK.name());
        response.setMessage(ResponseMessages.MESSAGE_SUCCESSFULLY);
        response.setData(employeeModel);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);

    }

    @PostMapping("/create-employee")
    public ResponseEntity<?> save(@RequestBody EmployeeDto employeeDto) {

        employeeService.save(employeeDto);

        ResponseDto response = ResponseDto.create();
        response.setStatus(HttpStatus.CREATED.name());
        response.setMessage(String.format(ResponseMessages.MESSAGE_CREATED, "Employee"));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
