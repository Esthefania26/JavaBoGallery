package com.bogallery.bogalleryApp.repository;


import com.bogallery.bogalleryApp.entities.Novedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NovedadRepository extends JpaRepository<Novedad,Long> {
    @Query(value = "select p From Novedad p where p.id=id")
    public Novedad findById(int id);
}
