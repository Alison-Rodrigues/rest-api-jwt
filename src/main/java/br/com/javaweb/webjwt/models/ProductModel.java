package br.com.javaweb.webjwt.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description;

    private String sku;

    @JoinColumn(name = "employee_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private EmployeeModel employee;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
