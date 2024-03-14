package com.bogallery.bogalleryApp.service;

import com.bogallery.bogalleryApp.entities.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoService {

    public List<Pago> findAll() throws Exception;

    public Pago findById(Long id);

    public void create(Pago pago);
    public void update(Pago pago);
    public void delete(Pago pago);
}
