package br.com.cleyton.apiProdutos.ApiProdutos.exception;

public class EmptyRequestBodyException extends RuntimeException{
    public EmptyRequestBodyException(String message) {
        super(message);
    }

    public EmptyRequestBodyException(String message, Throwable cause) {
        super(message, cause);
    }
}
