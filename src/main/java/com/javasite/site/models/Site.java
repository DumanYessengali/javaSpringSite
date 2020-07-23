package com.javasite.site.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private String title, products, recipes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getRecipes() {
        return recipes;
    }

    public void setRecipes(String recipes) {
        this.recipes = recipes;
    }

    public Site() {
    }

    public Site(String title, String products, String recipes) {
        this.title = title;
        this.products = products;
        this.recipes = recipes;
    }
}
