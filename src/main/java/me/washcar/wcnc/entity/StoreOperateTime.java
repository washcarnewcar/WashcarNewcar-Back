package me.washcar.wcnc.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreOperateTime {

  @Id
  @GeneratedValue
  private Long id;

  private LocalTime sundayStartTime;

  private LocalTime sundayEndTime;

  private LocalTime mondayStartTime;

  private LocalTime mondayEndTime;

  private LocalTime tuesdayStartTime;

  private LocalTime tuesdayEndTime;

  private LocalTime wednesdayStartTime;

  private LocalTime wednesdayEndTime;

  private LocalTime thursdayStartTime;

  private LocalTime thursdayEndTime;

  private LocalTime fridayStartTime;

  private LocalTime fridayEndTime;

  private LocalTime saturdayStartTime;

  private LocalTime saturdayEndTime;

  @OneToOne
  private Store store;

}
