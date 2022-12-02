package com.example.demo.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")

@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long id_client;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name" , nullable = false)
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany
    @JoinColumn(name = "fk_request")
    List<Requests> requests = new ArrayList<>();

}
