package com.bogallery.bogalleryApp.repository;

import com.bogallery.bogalleryApp.entities.Audio_Relato;
import com.bogallery.bogalleryApp.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    public Rol findById(int id);
}
