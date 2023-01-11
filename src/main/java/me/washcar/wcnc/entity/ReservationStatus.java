package me.washcar.wcnc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Table(name = "reservation_status")
public class ReservationStatus {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String type;

  @Getter
  @RequiredArgsConstructor
  public enum Reservation_Status {

    REQUEST(1L, "request"),
    RESERVATION(2L, "reservation"),
    REJECT(3L, "reject"),
    CANCEL(4L, "cancel"),
    COMPLETE(5L, "complete");

    private final Long id;

    private final String type;

  }

}
