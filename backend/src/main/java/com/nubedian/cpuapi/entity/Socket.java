package com.nubedian.cpuapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "socket")
public class Socket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Long getId() { return id; }
    public String getName() { return name; }
}
