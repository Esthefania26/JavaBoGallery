package com.bogallery.bogalleryApp.repository;

import com.bogallery.bogalleryApp.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {
    @Query(value = "select p From Venta p where p.id=id")
    public Venta findById(int id);
}
