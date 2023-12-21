package br.com.javaweb.webjwt.controllers;

import br.com.javaweb.webjwt.dtos.ProductDto;
import br.com.javaweb.webjwt.dtos.ResponseDto;
import br.com.javaweb.webjwt.models.ProductModel;
import br.com.javaweb.webjwt.repositories.ProductRepository;
import br.com.javaweb.webjwt.services.ProductService;
import br.com.javaweb.webjwt.util.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<?> listAll() {
        List<ProductModel> productModel = productService.listAll();

        if (productModel.size() == 0) {
            ResponseDto response = ResponseDto.create();
            response.setStatus(HttpStatus.NO_CONTENT.name());
            response.setMessage(ResponseMessages.MESSAGE_NO_CONTENT);
            response.setData(productModel);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        }

        ResponseDto response = ResponseDto.create();
        response.setStatus(HttpStatus.OK.name());
        response.setMessage(ResponseMessages.MESSAGE_SUCCESSFULLY);
        response.setData(productModel);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable Integer id) {
        Optional<ProductModel> productModel = productService.findById(id);

        if (productModel.isEmpty()) {
            ResponseDto response = ResponseDto.create();
            response.setStatus(HttpStatus.NOT_FOUND.name());
            response.setMessage(String.format(ResponseMessages.MESSAGE_NOT_FOUND, "Product"));

            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        }

        ResponseDto response = ResponseDto.create();
        response.setStatus(HttpStatus.OK.name());
        response.setMessage(ResponseMessages.MESSAGE_SUCCESSFULLY);
        response.setData(productModel);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/product")
    public ResponseEntity<ResponseDto> save(@RequestBody ProductDto productDto) {
        productService.save(productDto);

        ResponseDto response = ResponseDto.create();
        response.setStatus(HttpStatus.CREATED.name());
        response.setMessage(String.format(ResponseMessages.MESSAGE_CREATED, "Product"));
        response.setData(productDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
