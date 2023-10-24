package br.com.cleyton.apiProdutos.ApiProdutos.exception;

public class EmptyPageException extends RuntimeException{

    public EmptyPageException(String message) {
        super(message);
    }

    public EmptyPageException(String message, Throwable cause) {
        super(message, cause);
    }
}
