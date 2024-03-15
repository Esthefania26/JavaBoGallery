package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Categoria;
import com.bogallery.bogalleryApp.repository.CategoriaRepository;
import com.bogallery.bogalleryApp.service.CategoriaService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaImp implements CategoriaService {

    @Autowired

    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() throws Exception{

        return this.categoriaRepository.findAll();
    }

    @Override
    public Categoria findById(Long id) {

        return this.categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Categoria categoria) {
        this.categoriaRepository.save(categoria);

    }

    @Override
    public void update(Categoria categoria) {
        this.categoriaRepository.save(categoria);

    }

    @Override
    public void delete(Categoria categoria) {
        this.categoriaRepository.save(categoria);
    }


}
