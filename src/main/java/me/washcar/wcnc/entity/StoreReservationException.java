package me.washcar.wcnc.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreReservationException {

  @Id
  @GeneratedValue
  private Long reservationExceptionId;

  private boolean allDay;

  private LocalDateTime startDateTime;

  private LocalDateTime endDateTime;

  @ManyToOne
  private Store store;

}
