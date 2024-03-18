package com.bogallery.bogalleryApp.service;

import com.bogallery.bogalleryApp.entities.GuiaTurista;

import java.util.List;
import java.util.Optional;

public interface GuiaTuristaService {
    public List<GuiaTurista> findAll() throws Exception;

    public GuiaTurista findById(long id);

    public void create(GuiaTurista guiaTurista);
    public void update(GuiaTurista guiaTurista);
    public void  delete(GuiaTurista guiaTurista);
}
