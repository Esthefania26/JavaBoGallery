package com.bogallery.bogalleryApp.service;


import com.bogallery.bogalleryApp.entities.Lugar;

import java.util.List;
import java.util.Optional;
public interface LugarService {

    public List<Lugar> findAll() throws  Exception;

    public Lugar findById(Long id);

    public void create(Lugar lugar);
    public void update(Lugar lugar);
    public void delete(Lugar lugar);


}
