package com.thesevensky.starter.properties.movies.page;

public class MoviesPageProperties {
    private int pageSize = 8;

    private int cartPageSize = 4;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCartPageSize() {
        return cartPageSize;
    }

    public void setCartPageSize(int cartPageSize) {
        this.cartPageSize = cartPageSize;
    }
}
