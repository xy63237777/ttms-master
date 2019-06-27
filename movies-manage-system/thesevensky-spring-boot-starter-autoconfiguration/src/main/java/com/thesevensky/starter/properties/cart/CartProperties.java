package com.thesevensky.starter.properties.cart;

import com.thesevensky.starter.properties.cart.page.CartPageProperties;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/14 5:18
 * @Version 1.0
 */
public class CartProperties {
    private CartPageProperties page = new CartPageProperties();

    public CartPageProperties getPage() {
        return page;
    }

    public void setPage(CartPageProperties page) {
        this.page = page;
    }
}
