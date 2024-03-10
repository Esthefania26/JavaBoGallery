package com.bogallery.bogalleryApp.repository;

import com.bogallery.bogalleryApp.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    @Query(value = "select p From Categoria p where p.id=id")
    public Categoria findById(int id);

}
