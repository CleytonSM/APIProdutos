package br.com.cleyton.cadastraProdutos.exception.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class ApiExceptionTests {

    private ApiException apiException;
    @BeforeEach
    public void init() {
        HttpStatus status = mock(HttpStatus.class);
        ZonedDateTime timeStamp = mock(ZonedDateTime.class);
        apiException = new ApiException(status, "", timeStamp);
    }
    @Test
    public void ApiException_InstancingApiException() {
        assertThat(apiException).isInstanceOf(ApiException.class).isNotNull();
    }

    @Test
    public void ApiException_Data() {
        String message = apiException.getMessage();
        HttpStatus status = apiException.getStatus();
        ZonedDateTime timeStamp = apiException.getTimeStamp();
        ApiException apiException2 = new ApiException(status, "", timeStamp);
        boolean isEquals = apiException2.equals(apiException);
        assertThat(message).isNotNull();
        assertThat(status).isNotNull();
        assertThat(timeStamp).isNotNull();
        assertThat(isEquals).isTrue();
    }
}
