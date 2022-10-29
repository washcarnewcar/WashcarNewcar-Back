package me.washcar.wcnc.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
public class Brand {

    @Id
    @GeneratedValue
    private Long brandId;

    @Column(length = 127)
    private String brand;

    @OneToMany(mappedBy = "brand")
    private List<Model> models = new ArrayList<>();
}
