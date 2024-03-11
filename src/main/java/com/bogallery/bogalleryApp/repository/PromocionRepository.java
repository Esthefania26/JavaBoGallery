package com.bogallery.bogalleryApp.repository;

import com.bogallery.bogalleryApp.entities.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion,Long> {
    @Query(value = "select p From Promocion p where p.id=id")
    public Promocion findById(int id);

}
