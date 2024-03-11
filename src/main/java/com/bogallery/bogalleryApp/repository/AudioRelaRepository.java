package com.bogallery.bogalleryApp.repository;

import com.bogallery.bogalleryApp.entities.Audio_Relato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioRelaRepository extends JpaRepository<Audio_Relato,Long> {

    public Audio_Relato findById(int id);
}
