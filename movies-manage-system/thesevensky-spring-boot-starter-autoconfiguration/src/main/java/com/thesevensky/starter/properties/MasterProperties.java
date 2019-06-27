package com.thesevensky.starter.properties;

import com.thesevensky.starter.properties.amqp.AmqpProperties;
import com.thesevensky.starter.properties.cart.CartProperties;
import com.thesevensky.starter.properties.elastic.ElasticProperties;
import com.thesevensky.starter.properties.email.EmailProperties;
import com.thesevensky.starter.properties.file.FileProperties;
import com.thesevensky.starter.properties.movies.MoviesProperties;
import com.thesevensky.starter.properties.security.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "thesevensky")
public class MasterProperties {

    private MoviesProperties movies = new MoviesProperties();

    private SecurityProperties security = new SecurityProperties();

    private AmqpProperties amqp = new AmqpProperties();

    private FileProperties file = new FileProperties();

    private ElasticProperties elastic = new ElasticProperties();

    private EmailProperties email = new EmailProperties();

    private CartProperties cart = new CartProperties();

    public MoviesProperties getMovies() {
        return movies;
    }

    public void setMovies(MoviesProperties movies) {
        this.movies = movies;
    }

    public SecurityProperties getSecurity() {
        return security;
    }

    public void setSecurity(SecurityProperties security) {
        this.security = security;
    }

    public AmqpProperties getAmqp() {
        return amqp;
    }

    public void setAmqp(AmqpProperties amqp) {
        this.amqp = amqp;
    }

    public FileProperties getFile() {
        return file;
    }

    public void setFile(FileProperties file) {
        this.file = file;
    }

    public ElasticProperties getElastic() {
        return elastic;
    }

    public void setElastic(ElasticProperties elastic) {
        this.elastic = elastic;
    }

    public EmailProperties getEmail() {
        return email;
    }

    public void setEmail(EmailProperties email) {
        this.email = email;
    }

    public CartProperties getCart() {
        return cart;
    }

    public void setCart(CartProperties cart) {
        this.cart = cart;
    }
}
