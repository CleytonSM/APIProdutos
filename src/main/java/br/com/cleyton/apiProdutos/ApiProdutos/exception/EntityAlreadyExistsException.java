package br.com.cleyton.apiProdutos.ApiProdutosTests.exception;

public class EntityAlreadyExistsException extends RuntimeException{
    public EntityAlreadyExistsException(String message) {
        super(message);
    }

}
