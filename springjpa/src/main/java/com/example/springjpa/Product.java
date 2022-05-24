package com.example.springjpa;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private int id;

    private String name;

    private double price;

    @Builder
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
