package br.com.cleyton.cadastraProdutos.model.product;


import br.com.cleyton.cadastraProdutos.dto.product.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @CreationTimestamp
    private LocalDateTime createdAt;

    public ProductModel(ProductDto data) {
        this.name = data.getName();
        this.description = data.getDescription();
        this.price = data.getPrice();
        this.quantity = data.getQuantity();
        this.barCode = data.getBarCode();
        this.manufacturingDate = data.getManufacturingDate();
        this.expirationDate = data.getExpirationDate();
    }
}
