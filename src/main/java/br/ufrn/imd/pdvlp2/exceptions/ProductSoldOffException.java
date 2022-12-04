package br.ufrn.imd.pdvlp2.exceptions;

public class ProductSoldOffException extends Exception {
    public ProductSoldOffException(String errorMessage){
        super(errorMessage);
    }
}
