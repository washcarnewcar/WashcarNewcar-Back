package me.washcar.wcnc.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Builder
  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

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

