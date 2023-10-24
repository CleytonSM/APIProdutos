package br.com.cleyton.apiProdutos.ApiProdutos.model.product;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer quantity;
    @Column(unique = true, nullable = false)
    private Long barCode;
    @Column(nullable = false)
    private LocalDateTime manufacturingDate;
    @Column(nullable = false)
    private LocalDateTime expirationDate;
    private Boolean isDeleted = Boolean.FALSE;
    @CreationTimestamp
    private LocalDateTime createdAt;

}
