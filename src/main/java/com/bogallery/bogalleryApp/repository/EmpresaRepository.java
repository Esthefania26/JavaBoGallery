package com.bogallery.bogalleryApp.repository;

import com.bogallery.bogalleryApp.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,Long> {


    public Empresa findById(int id);
}
