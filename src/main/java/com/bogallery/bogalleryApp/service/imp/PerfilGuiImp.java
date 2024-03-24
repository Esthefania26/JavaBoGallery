package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.PerfilGuia;
import com.bogallery.bogalleryApp.repository.PerlfilGuiaRepository;
import com.bogallery.bogalleryApp.service.PerfilGuiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilGuiImp implements PerfilGuiService {

    @Autowired
    private PerlfilGuiaRepository perlfilGuiaRepository;
    @Override
    public List<PerfilGuia> findAll() throws Exception
    {
        return this.perlfilGuiaRepository.findAll();
    }

    @Override
    public PerfilGuia findById(Long id)
    {
        return this.perlfilGuiaRepository.findById(id).orElse(null);

    }
    @Override
    public void create(PerfilGuia perfilGuia)
    {
        this.perlfilGuiaRepository.save(perfilGuia);
    }
    @Override
    public void update(PerfilGuia perfilGuia)
    {
        this.perlfilGuiaRepository.save(perfilGuia);
    }
    @Override
    public void delete(PerfilGuia perfilGuia)
    {
        this.perlfilGuiaRepository.delete(perfilGuia);
    }
}
