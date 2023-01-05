package me.washcar.wcnc.entity;

import java.time.LocalDateTime;
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

  private String sundayStartTime;

  private String sundayEndTime;

  private String mondayStartTime;

  private String mondayEndTime;

  private String tuesdayStartTime;

  private String tuesdayEndTime;

  private String wednesdayStartTime;

  private String wednesdayEndTime;

  private String thursdayStartTime;

  private String thursdayEndTime;

  private String fridayStartTime;

  private String fridayEndTime;

  private String saturdayStartTime;

  private String saturdayEndTime;

  @OneToOne
  private Store store;

}
