package me.washcar.wcnc.entity;

import java.time.LocalDateTime;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.NoArgsConstructor;

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

  private LocalDateTime endDate;

  @OneToOne
  private Model model;

  @ManyToOne
  private User user;

  @ManyToOne
  private Store store;

  @ManyToOne
  private StoreMenu storeMenu;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "status_id")
  private ReservationStatus reservationStatus;

//    @ManyToOne
//    private Slot slot;
//
//    @ManyToOne
//    private SlotOption slotOption;
}
