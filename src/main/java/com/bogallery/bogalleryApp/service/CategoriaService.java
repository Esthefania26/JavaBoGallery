package com.bogallery.bogalleryApp.service;

import com.bogallery.bogalleryApp.entities.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {


    public List<Categoria> findAll() throws Exception;

    public Categoria findById(int id);

    public void create(Categoria categoria);
    public void update(Categoria categoria);
    public void  delete(Categoria categoria);

}
