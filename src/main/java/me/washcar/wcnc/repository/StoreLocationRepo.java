package me.washcar.wcnc.repository;

import me.washcar.wcnc.entity.StoreLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreLocationRepo extends JpaRepository<StoreLocation, Long> {
}
