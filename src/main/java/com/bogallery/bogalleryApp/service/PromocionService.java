package com.bogallery.bogalleryApp.service;

import com.bogallery.bogalleryApp.entities.Promocion;

import java.util.List;
import java.util.Optional;

public interface PromocionService {
    public List<Promocion> findAll() throws Exception;

    public Promocion findById(Long id);

    public void create(Promocion promocion);
    public void update(Promocion promocion);
    public void delete(Promocion promocion);
}

