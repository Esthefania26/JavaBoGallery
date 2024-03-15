package com.bogallery.bogalleryApp.service;

import com.bogallery.bogalleryApp.entities.Audio_Relato;

import java.util.List;
import java.util.Optional;
public interface AudioRelaService {

    public List<Audio_Relato> findAll() throws Exception;

    public  Audio_Relato findById(Long id);

    public void create(Audio_Relato audio_relato);

    public  void update(Audio_Relato audio_relato);

    public  void delete(Audio_Relato audio_relato);

}
