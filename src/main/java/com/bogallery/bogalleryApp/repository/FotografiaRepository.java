package com.bogallery.bogalleryApp.repository;

import com.bogallery.bogalleryApp.entities.Audio_Relato;
import com.bogallery.bogalleryApp.entities.Fotografia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotografiaRepository extends JpaRepository<Fotografia,Long> {

    public Fotografia findById(int id);
}
