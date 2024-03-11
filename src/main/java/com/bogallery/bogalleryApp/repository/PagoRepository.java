package com.bogallery.bogalleryApp.repository;

import com.bogallery.bogalleryApp.entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago,Long> {
    @Query(value = "select p From Pago p where p.id=id")
    public Pago findById(int id);
}
