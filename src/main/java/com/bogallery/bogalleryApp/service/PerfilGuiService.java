package com.bogallery.bogalleryApp.service;

import com.bogallery.bogalleryApp.entities.PerfilGuia;

import java.util.List;
import java.util.Optional;
public interface PerfilGuiService {

    public List<PerfilGuia> findAll() throws  Exception;

    public PerfilGuia findById(Long id);

    public void create(PerfilGuia perfilGuia);

    public void update(PerfilGuia perfilGuia);

    public void delete(PerfilGuia perfilGuia);



}
