package br.com.cleyton.apiProdutos.ApiProdutosTests.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Long barCode;
    private LocalDateTime manufacturingDate;
    private LocalDateTime expirationDate;
}
