package br.com.cleyton.apiProdutos.ApiProdutosTests.exception.builder;

import br.com.cleyton.apiProdutos.ApiProdutosTests.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class ApiExceptionHandlerTests {

    private ApiExceptionHandler apiExceptionHandler;
    @BeforeEach
    public void init(){
        apiExceptionHandler = new ApiExceptionHandler();
    }
    @Test
    public void ApiExceptionHandler_ProductAlreadyExistsException_ReturnsNewResponseEntity() {
        EntityAlreadyExistsException e = mock(EntityAlreadyExistsException.class);
        ResponseEntity<Object> response = apiExceptionHandler.productAlreadyExistsException(e);

        assertThat(response).isNotNull();
    }

    @Test
    public void ApiExceptionHandler_TermsMissingException_ReturnsNewResponseEntity() {
        MissingTermsException e = mock(MissingTermsException.class);
        ResponseEntity<Object> response = apiExceptionHandler.termsMissingException(e);

        assertThat(response).isNotNull();
    }

    @Test
    public void ApiExceptionHandler_EntityNotFoundException_ReturnsNewResponseEntity() {
        EntityNotFoundException e = mock(EntityNotFoundException.class);
        ResponseEntity<Object> response = apiExceptionHandler.entityNotFoundException(e);

        assertThat(response).isNotNull();
    }

    @Test
    public void ApiExceptionHandler_EmptyPageException_ReturnsNewResponseEntity() {
        EmptyPageException e = mock(EmptyPageException.class);
        ResponseEntity<Object> response = apiExceptionHandler.emptyPageException(e);

        assertThat(response).isNotNull();
    }

    @Test
    public void ApiExceptionHandler_EmptyRequestBodyException_ReturnsNewResponseEntity() {
        EmptyRequestBodyException e = mock(EmptyRequestBodyException.class);
        ResponseEntity<Object> response = apiExceptionHandler.emptyRequestBodyException(e);

        assertThat(response).isNotNull();
    }
}
