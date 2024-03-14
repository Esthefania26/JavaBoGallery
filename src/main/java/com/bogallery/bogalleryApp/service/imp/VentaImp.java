package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Venta;
import com.bogallery.bogalleryApp.repository.VentaRepository;
import com.bogallery.bogalleryApp.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaImp implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> findAll() throws Exception {
        return this.ventaRepository.findAll();
    }

    // Elimina la anotación @Autowired de este método
    @Override
    public Venta findById(Long id) {
        return this.ventaRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Venta venta) {
        this.ventaRepository.save(venta);
    }

    @Override
    public void update(Venta venta) {
        this.ventaRepository.save(venta);
    }

    @Override
    public void delete(Venta venta) {
        this.ventaRepository.delete(venta);
    }
}
