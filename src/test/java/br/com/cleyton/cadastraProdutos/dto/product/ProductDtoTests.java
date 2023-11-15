package br.com.cleyton.cadastraProdutos.dto.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class ProductDtoTests {


    @Test
    public void ProductDto_InstancingProductDto_NoArgsConstructor() {
        ProductDto productNoArgs = new ProductDto();

        assertThat(productNoArgs).isInstanceOf(ProductDto.class);
    }

    @Test
    public void ProductDto_InstancingProductDto_AllArgsConstructor() {
        ProductDto productAllArgs = new ProductDto("name"
        ,"desc", 20.00, 20, 12847L, LocalDateTime.now(), LocalDateTime.now());

        assertThat(productAllArgs).isInstanceOf(ProductDto.class);
    }
}
