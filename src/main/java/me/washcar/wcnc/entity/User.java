package me.washcar.wcnc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long userId;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  private String nickname;

//    @Column(nullable = false)
//    private ZonedDateTime createDateTime;
//
//    @Column(nullable = false)
//    private ZonedDateTime lastLoginDateTime;

  @ManyToMany(fetch = FetchType.LAZY)
  private Set<Store> stores = new HashSet<>();

  @ManyToMany(fetch = FetchType.LAZY)
  private Set<Role> roles = new HashSet<>();

  @OneToMany(mappedBy = "user")
  private List<Reservation> reservations = new ArrayList<>();

}

