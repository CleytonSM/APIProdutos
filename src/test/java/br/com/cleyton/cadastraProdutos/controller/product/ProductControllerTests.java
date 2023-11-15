package br.com.cleyton.cadastraProdutos.controller.product;

import br.com.cleyton.cadastraProdutos.model.product.ProductModel;
import br.com.cleyton.cadastraProdutos.service.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ProductController.class)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService service;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductController controller;
    private ProductModel mockProduct;

    @BeforeEach
    public void init() {
        mockProduct = new ProductModel();
        mockProduct.setBarCode(112312534L);
        mockProduct.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        mockProduct.setDescription("computador bacana");
        mockProduct.setExpirationDate(LocalDate.of(2023, 1, 1).atStartOfDay());
        mockProduct.setId(1);
        mockProduct.setManufacturingDate(LocalDate.of(2023, 1, 1).atStartOfDay());
        mockProduct.setName("computador");
        mockProduct.setPrice(10.0);
        mockProduct.setQuantity(10);
    }

    @Test
    public void ProductController_CreateProduct_ReturnProduct() throws Exception {
        given(service.createProduct(ArgumentMatchers.any())).willReturn(mockProduct);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockProduct));

        MockMvcBuilders.standaloneSetup(controller).build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void ProductController_GetAllPageable_ReturnsProducts() throws Exception {
        Stream<ProductModel> allProducts = Mockito.mock(Stream.class);
        when(service.getAllProductsPageable(Mockito.<Integer>any())).thenReturn(allProducts);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/get-all/page={pageNumber}", 0)
                .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(controller).build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ProductController_GetAll_ReturnsProducts() throws Exception {
        when(service.getAllProducts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/product/get-all")
                        .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(controller).build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ProductController_GetById_ReturnsProducts() throws Exception {
        when(service.getProductById(Mockito.<Integer>any())).thenReturn(mockProduct);
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/product/get/{id}", mockProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(controller).build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ProductController_DeleteProductById() throws Exception {
        when(service.deleteProductById(Mockito.<Integer>any())).thenReturn(mockProduct);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/product/delete/id={id}", mockProduct.getId())
                .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ProductController_DeleteProductByBarCode() throws Exception {
        when(service.deleteProductByBarCode(Mockito.<Long>any())).thenReturn(mockProduct);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/product/delete/barCode={barCode}", mockProduct.getBarCode())
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ProductController_UpdateById_ReturnsProduct() throws Exception {
        given(service.fullProductUpdateById(Mockito.<Integer>any(), Mockito.any())).willReturn(mockProduct);

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.put("/product/update/id={id}", mockProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockProduct));

        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ProductController_UpdateByBarCode_ReturnsProduct() throws Exception {
        given(service.fullProductUpdateByBarCode(Mockito.<Long>any(), Mockito.any())).willReturn(mockProduct);

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.put("/product/update/barCode={barCode}", mockProduct.getBarCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockProduct));

        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ProductController_PartialUpdateById_ReturnsProduct() throws Exception {
        given(service.partialProductUpdateById(Mockito.<Integer>any(), Mockito.any())).willReturn(mockProduct);

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.patch("/product/partial-update/id={id}", mockProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockProduct));

        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ProductController_PartialUpdateByBarCode_ReturnsProduct() throws Exception {
        given(service.partialProductUpdateByBarCode(Mockito.<Long>any(), Mockito.any())).willReturn(mockProduct);

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.patch("/product/partial-update/barCode={barCode}", mockProduct.getBarCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockProduct));

        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
