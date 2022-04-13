package com.example.assigment81.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @NotNull
    private String name;
    private int age;




}
