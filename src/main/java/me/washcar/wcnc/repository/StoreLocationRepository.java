package me.washcar.wcnc.repository;

import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreLocationRepository extends JpaRepository<StoreLocation, Long> {
    StoreLocation findByStore(Store store);
}
