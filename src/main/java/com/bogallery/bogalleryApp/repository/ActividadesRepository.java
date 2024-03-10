package com.bogallery.bogalleryApp.repository;

import com.bogallery.bogalleryApp.entities.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ActividadesRepository extends JpaRepository<Actividad,Long> {
}
