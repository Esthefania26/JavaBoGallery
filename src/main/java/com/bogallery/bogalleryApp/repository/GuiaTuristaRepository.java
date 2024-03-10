package com.bogallery.bogalleryApp.repository;

import com.bogallery.bogalleryApp.entities.GuiaTurista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface GuiaTuristaRepository extends JpaRepository<GuiaTurista,Long> {

    @Query(value = "select p From GuiaTurista p where p.id=id")
    public GuiaTurista findById(int id);
}
