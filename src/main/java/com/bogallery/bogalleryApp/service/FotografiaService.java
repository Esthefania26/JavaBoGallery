package com.bogallery.bogalleryApp.service;

import com.bogallery.bogalleryApp.entities.Fotografia;

import java.util.List;
import java.util.Optional;
public interface FotografiaService {
    public List<Fotografia> findAll() throws Exception;

    public Fotografia findById(Long id);

    public  void create(Fotografia fotografia);

    public  void update(Fotografia fotografia);

    public  void delete(Fotografia fotografia);

}
