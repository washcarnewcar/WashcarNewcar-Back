package me.washcar.wcnc.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Model {

  @Id
  @GeneratedValue
  private Long id;

  @Column(length = 127)
  private String name;

  @ManyToOne
  private Brand brand;
}
