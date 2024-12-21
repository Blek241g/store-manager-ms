package org.scalke.productsservice.constants;

public enum ProductState {
    NEW("new"), DAMAGED("damaged");

    private final String state;

    ProductState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}
