package br.com.cleyton.apiProdutos.ApiProdutos.exception;

public class TermsMissingException extends RuntimeException {

    public TermsMissingException(String message) {
        super(message);
    }

    public TermsMissingException(String message, Throwable cause) {
        super(message, cause);
    }
}
