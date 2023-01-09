package me.washcar.wcnc.entity;

import java.time.LocalDateTime;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice.Local;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

  @Id
  @GeneratedValue
  private Long id;

  private String tel;

  private LocalDateTime date;

  private String request;

  private String carNumber;

  @OneToOne
  private Model model;

  @ManyToOne
  private User user;

  @ManyToOne
  private Store store;

  @ManyToOne
  private StoreMenu storeMenu;

//    @ManyToOne
//    private Slot slot;
//
//    @ManyToOne
//    private SlotOption slotOption;
}
