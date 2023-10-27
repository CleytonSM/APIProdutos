package br.com.cleyton.apiProdutos.ApiProdutosTests.exception;

public class EmptyRequestBodyException extends RuntimeException{
    public EmptyRequestBodyException(String message) {
        super(message);
    }

}
