package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Empresa;
import com.bogallery.bogalleryApp.repository.EmpresaRepository;
import com.bogallery.bogalleryApp.service.EmpresaServicie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmpresaImp implements EmpresaServicie {
    @Autowired

    private EmpresaRepository empresaRepository;


    @Override
    public List<Empresa> findAll() throws Exception{
        return this.empresaRepository.findAll();
    }

    @Override
    public Empresa findById(int id){

        return this.empresaRepository.findById(id);
    }

    @Override
    public void  create(Empresa empresa){
        this.empresaRepository.save(empresa);
    }


    @Override
    public void update(Empresa empresa){
        this.empresaRepository.save(empresa);
    }


    @Override
    public void delete(Empresa empresa){
        this.empresaRepository.save(empresa);
    }



}
