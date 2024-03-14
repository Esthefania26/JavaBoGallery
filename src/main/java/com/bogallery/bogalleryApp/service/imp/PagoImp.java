package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Pago;
import com.bogallery.bogalleryApp.repository.PagoRepository;
import com.bogallery.bogalleryApp.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoImp implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public List<Pago> findAll() throws Exception {
        return this.pagoRepository.findAll();
    }

    // Este método no debería tener la anotación @Autowired
    @Override
    public Pago findById(Long id) {
        return this.pagoRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Pago pago) {
        this.pagoRepository.save(pago);
    }

    @Override
    public void update(Pago pago) {
        this.pagoRepository.save(pago);
    }

    @Override
    public void delete(Pago pago) {
        this.pagoRepository.delete(pago);
    }
}
