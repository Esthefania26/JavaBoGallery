package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Fotografia;
import com.bogallery.bogalleryApp.repository.FotografiaRepository;
import com.bogallery.bogalleryApp.service.FotografiaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class FotografiaImp implements FotografiaService {
    @Autowired
    private FotografiaRepository fotografiaRepository;

    @Override
    public List<Fotografia> findAll() throws Exception
    {
        return this.fotografiaRepository.findAll();
    }
    @Override
    public Fotografia findById(int id)
    {
        return this.fotografiaRepository.findById(id);
    }

    @Override
    public  void create(Fotografia fotografia)
    {
        this.fotografiaRepository.save(fotografia);
    }

    @Override
    public  void update(Fotografia fotografia)
    {
        this.fotografiaRepository.save(fotografia);
    }

    @Override
    public  void delete(Fotografia fotografia)
    {
        this.fotografiaRepository.delete(fotografia);
    }

}
