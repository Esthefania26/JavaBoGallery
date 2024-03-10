package com.bogallery.bogalleryApp.repository;

import com.bogallery.bogalleryApp.entities.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LugarRepository extends JpaRepository<Lugar,Long> {

}
