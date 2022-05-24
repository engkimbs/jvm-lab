package com.example.springjpa;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="users")
@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int age;

    @Column(unique=true, nullable = false)
    private String email;

    private Integer status;

    @Builder
    public User(int id, String name, int age, String email, Integer status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.status = status;
    }
}