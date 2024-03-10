package com.bogallery.bogalleryApp.repository;

import com.bogallery.bogalleryApp.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion,Long> {

    @Query(value = "select p From Inscripcion p where p.id=id")
    public Inscripcion findById(int id);
}
