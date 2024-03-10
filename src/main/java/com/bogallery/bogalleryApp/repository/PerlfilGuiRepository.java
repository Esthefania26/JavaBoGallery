package com.bogallery.bogalleryApp.repository;

import com.bogallery.bogalleryApp.entities.Audio_Relato;
import com.bogallery.bogalleryApp.entities.PerfilGuia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerlfilGuiRepository extends JpaRepository<PerfilGuia,Long> {

    public PerfilGuia findById(int id);
}
