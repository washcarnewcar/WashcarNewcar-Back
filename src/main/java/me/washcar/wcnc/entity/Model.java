package me.washcar.wcnc.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Model {
    @Id
    @GeneratedValue
    private long idModel;

    @Column(length = 127)
    private String model;

    @ManyToOne
    private Brand brand;
}
