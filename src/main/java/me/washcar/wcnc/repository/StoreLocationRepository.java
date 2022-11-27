package me.washcar.wcnc.repository;

import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreLocationRepository extends JpaRepository<StoreLocation, Long> {
    Optional<StoreLocation> findByStore(Store store);
}
