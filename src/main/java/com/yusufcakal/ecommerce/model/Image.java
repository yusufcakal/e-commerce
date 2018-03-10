package com.yusufcakal.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "images", schema = "public")
public class Image{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Column(name = "product_id")
    private int product_id;

    public Image(String name, String path, int product_id) {
        this.name = name;
        this.path = path;
        this.product_id = product_id;
    }

    public Image() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
