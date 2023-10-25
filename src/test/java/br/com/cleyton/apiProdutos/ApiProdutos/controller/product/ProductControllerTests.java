package br.com.cleyton.apiProdutos.ApiProdutos.controller.product;

import br.com.cleyton.apiProdutos.ApiProdutos.dto.product.ProductDto;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductModel;
import br.com.cleyton.apiProdutos.ApiProdutos.service.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = ProductController.class)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService service;
    @Autowired
    private ObjectMapper objectMapper;
    private ProductModel mockProduct;
    private ProductDto productDto;

    @BeforeEach
    public void init() {
        mockProduct = ProductModel.builder().name("computador").description("computador bacana")
                .price(2599.99).quantity(20).barCode(123456634123424L).manufacturingDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now()).build();
        productDto = ProductDto.builder().name("computador").description("computador bacana")
                .price(2599.99).quantity(20).barCode(123456634123424L).manufacturingDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now()).build();
    }

    @Test
    public void ProductController_CreateProduct_ReturnProduct() throws Exception {
        given(service.createProduct(ArgumentMatchers.any())).willReturn(mockProduct);

        ResultActions response = mockMvc.perform(post("/product/create")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void ProductController_GetAllPageable_ReturnProducts() throws Exception {
        Stream<ProductModel> allProducts = Mockito.mock(Stream.class);
        when(service.getAllProductsPageable(0)).thenReturn(allProducts);
        ResultActions response = mockMvc.perform(get("/product/get-all/page={pageNumber}", 1)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
