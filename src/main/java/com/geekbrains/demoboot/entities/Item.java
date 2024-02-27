package com.geekbrains.demoboot.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue
    @Column (name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private int price;

    public Item(){}

    public Item(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
