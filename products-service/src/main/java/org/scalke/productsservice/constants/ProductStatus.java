package org.scalke.productsservice.constants;

public enum ProductStatus {
    LOADING("loading"), FINISHED("finished");

    private final String state;

    ProductStatus(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}
