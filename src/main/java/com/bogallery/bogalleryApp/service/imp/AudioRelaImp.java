package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Audio_Relato;
import com.bogallery.bogalleryApp.repository.AudioRelaRepository;
import com.bogallery.bogalleryApp.service.AudioRelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class AudioRelaImp implements AudioRelaService {
    @Autowired
    private AudioRelaRepository audioRelaRepository;

    @Override
    public List<Audio_Relato> findAll() throws Exception
    {
        return this.audioRelaRepository.findAll();
    }

    @Override
    public  Audio_Relato findById(Long id)
    {

        return this.audioRelaRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Audio_Relato audio_relato)
    {
        this.audioRelaRepository.save(audio_relato);
    }
    @Override
    public void update(Audio_Relato audio_relato)
    {
        this.audioRelaRepository.save(audio_relato);
    }
    @Override
    public void delete(Audio_Relato audio_relato)
    {
        this.audioRelaRepository.delete(audio_relato);
    }
}
