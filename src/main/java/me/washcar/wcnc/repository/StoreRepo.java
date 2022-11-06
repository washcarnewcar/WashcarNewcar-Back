package me.washcar.wcnc.repository;

import me.washcar.wcnc.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepo extends JpaRepository<Store, Long> {
    Store findBySlug(String slug);
}
