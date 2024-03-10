package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.PerfilGuia;
import com.bogallery.bogalleryApp.service.imp.PerfilGuiImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/perfilgui/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class PerfilGuiController {

    @Autowired
    PerfilGuiImp perfilGuiImp;
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Objects> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@" + request);
            PerfilGuia perfilGuia=new PerfilGuia();

            perfilGuia.setNombreG(request.get("NombreG").toString());

            perfilGuia.setApellidoG(request.get("ApellidoG").toString());

            perfilGuia.setCorreoG(request.get("CorreoG").toString());

            perfilGuia.setFormacion(request.get("Formacion").toString());

            perfilGuia.setIdiomaM(request.get("Idioma_materno").toString());

            perfilGuia.setSegundoI(request.get("Segundo_idioma").toString());

            perfilGuia.setTelefonoG(Integer.parseInt(request.get("TelefonoG").toString()));

            //perfilGuia.setCertificado(request.get("Certificado").getBytes());

            perfilGuia.setLenguaSena(Boolean.parseBoolean(request.get("Lengua_sena").toString()));

           /* GeneroGEnumType generoG = GeneroGEnumType.valueOf(request.get("GeneroG").toString());
            perfilGuia.setGeneroG(generoG);*/

            this.perfilGuiImp.create(perfilGuia);
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
