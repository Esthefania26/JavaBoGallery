package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Categoria;
import com.bogallery.bogalleryApp.service.imp.CategoriaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/categoria/")
@CrossOrigin("*")
public class CategoriaController {
    @Autowired
    CategoriaImp categoriaImp;

    @PostMapping("create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody Map<String,Object> request){
        Map<String,Object> response=new HashMap<>();

        try {
            System.out.println("@@@@"+request);
            Categoria categoria= new Categoria();


            categoria.setDescripcionC(request.get("descripcionC").toString());
this.categoriaImp.create(categoria);
            response.put("status","success");
            response.put("data","Registro Exitoso");
        }catch (Exception e){
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
