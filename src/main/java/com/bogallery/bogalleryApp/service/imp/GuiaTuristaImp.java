package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.GuiaTurista;
import com.bogallery.bogalleryApp.repository.GuiaTuristaRepository;
import com.bogallery.bogalleryApp.service.GuiaTuristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuiaTuristaImp implements GuiaTuristaService {

    @Autowired


    private GuiaTuristaRepository guiaTuristaRepository;

    @Override
    public List<GuiaTurista> findAll() throws Exception{
        return this.guiaTuristaRepository.findAll();
    }

    @Override
    public GuiaTurista findById(int id){
        return this.guiaTuristaRepository.findById(id);
    }
    @Override

    public void create(GuiaTurista guiaTurista){
        this.guiaTuristaRepository.save(guiaTurista);
    }

    public void  update(GuiaTurista guiaTurista){
        this.guiaTuristaRepository.save(guiaTurista);
    }

    public void delete(GuiaTurista guiaTurista){
        this.guiaTuristaRepository.save(guiaTurista);
    }
}
