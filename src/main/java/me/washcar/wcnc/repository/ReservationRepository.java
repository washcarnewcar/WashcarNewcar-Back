package me.washcar.wcnc.repository;

import me.washcar.wcnc.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
