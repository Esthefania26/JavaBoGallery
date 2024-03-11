package com.bogallery.bogalleryApp.service;

import com.bogallery.bogalleryApp.entities.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaService {

    public List<Venta> findAll() throws Exception;

    public Venta findById(int id);

    public void create(Venta venta);
    public void update(Venta venta);
    public void delete(Venta venta);
}
