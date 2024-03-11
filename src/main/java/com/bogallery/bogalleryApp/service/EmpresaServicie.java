package com.bogallery.bogalleryApp.service;

import com.bogallery.bogalleryApp.entities.Empresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaServicie {

    public List<Empresa> findAll() throws Exception;

    public Empresa findById(int id);

    public void create(Empresa empresa);
    public void update(Empresa empresa);
    public void  delete(Empresa empresa);

}
