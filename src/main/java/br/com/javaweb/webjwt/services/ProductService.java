package br.com.javaweb.webjwt.services;

import br.com.javaweb.webjwt.dtos.ProductDto;
import br.com.javaweb.webjwt.models.EmployeeModel;
import br.com.javaweb.webjwt.models.ProductModel;
import br.com.javaweb.webjwt.repositories.EmployeeRepository;
import br.com.javaweb.webjwt.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<ProductModel> listAll() {
        return productRepository.findAll();
    }

    public Optional<ProductModel> findById(Integer id) {
        return productRepository.findById(id);
    }

    public void save(ProductDto productDto) {
        ProductModel productModel = new ProductModel();

        EmployeeModel employee = employeeRepository.findById(productDto.getEmployeeId())
                        .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        productModel.setDescription(productDto.getDescription());
        productModel.setSku(productDto.getSku());
        productModel.setEmployee(employee);
        productModel.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        productModel.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));

        productRepository.save(productModel);
    }
}
