package br.com.cleyton.apiProdutos.ApiProdutosTests.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message) {
        super(message);
    }

}
