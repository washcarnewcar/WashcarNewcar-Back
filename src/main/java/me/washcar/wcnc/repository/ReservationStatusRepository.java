package me.washcar.wcnc.repository;

import me.washcar.wcnc.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationStatusRepository extends JpaRepository<ReservationStatus, Long> {

}
