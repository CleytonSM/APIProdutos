package br.com.cleyton.apiProdutos.ApiProdutos.model.product;

import java.time.LocalDateTime;

public record ProductUpdateRecordData(
        String name,
        String description,
        Double price,
        Integer quantity,
        Long barCode,
        LocalDateTime manufacturingDate,
        LocalDateTime expirationDate
) {
}
