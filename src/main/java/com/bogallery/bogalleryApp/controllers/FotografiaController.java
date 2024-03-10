package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Fotografia;
import com.bogallery.bogalleryApp.service.imp.FotografiaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/fotografia/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class FotografiaController {

    @Autowired
    FotografiaImp fotografiaImp;
    @PostMapping("create")

    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Objects> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@" + request);
            Fotografia fotografia=new Fotografia();

            fotografia.setDescripcionF(request.get("DescripcionF").toString());
            //fotografia.setFotografia((byte[]) request.get("Fotografia"));

            this.fotografiaImp.create(fotografia);
            response.put("status", "succes");
            response.put("data", "Registro Exitoso");

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
