package com.example.kakashi.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "kakashi")
public class KakashiEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custID;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;
}