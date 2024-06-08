package com.application.CNC.data;

import com.application.CNC.data.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByIdIn(List<Long> id);

    @Query("select p.id from #{#entityName} p")
    List<Long> getAllIds();

}
