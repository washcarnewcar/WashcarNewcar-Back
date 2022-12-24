package me.washcar.wcnc.repository;

import java.util.List;
import me.washcar.wcnc.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
  List<Model> findAllByBrand_Id(Long brandId);
}
