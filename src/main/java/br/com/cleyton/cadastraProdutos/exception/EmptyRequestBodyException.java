package br.com.cleyton.cadastraProdutos.exception;

public class EmptyRequestBodyException extends RuntimeException{
    public EmptyRequestBodyException(String message) {
        super(message);
    }

}
