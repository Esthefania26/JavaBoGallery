package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.PerfilGuia;
import com.bogallery.bogalleryApp.repository.PerlfilGuiRepository;
import com.bogallery.bogalleryApp.service.PerfilGuiService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
public class PerfilGuiImp implements PerfilGuiService {

    @Autowired
    private PerlfilGuiRepository perlfilGuiRepository;
    @Override
    public List<PerfilGuia> findAll() throws Exception
    {
        return this.perlfilGuiRepository.findAll();
    }

    @Override
    public PerfilGuia findById(int id)
    {
        return this.perlfilGuiRepository.findById(id);
    }
    @Override
    public void create(PerfilGuia perfilGuia)
    {
        this.perlfilGuiRepository.save(perfilGuia);
    }
    @Override
    public void update(PerfilGuia perfilGuia)
    {
        this.perlfilGuiRepository.save(perfilGuia);
    }
    @Override
    public void delete(PerfilGuia perfilGuia)
    {
        this.perlfilGuiRepository.save(perfilGuia);
    }
}
