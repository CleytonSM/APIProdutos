package br.com.cleyton.apiProdutos.ApiProdutos.exception;

public class MissingTermsException extends RuntimeException {

    public MissingTermsException(String message) {
        super(message);
    }

    public MissingTermsException(String message, Throwable cause) {
        super(message, cause);
    }
}
