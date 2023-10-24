package com.vhws.karaoke.repository;

import com.vhws.karaoke.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseRepository extends JpaRepository<House, Long> {
    @Query("SELECT h FROM House h WHERE h.open = :open")
    List<House> findByOpenStatus(@Param("open") boolean open);
}
