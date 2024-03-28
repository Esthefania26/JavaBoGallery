package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.PerfilGuia;
import com.bogallery.bogalleryApp.service.imp.PerfilGuiImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/perfilgui/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class PerfilGuiController {

    @Autowired
    PerfilGuiImp perfilGuiImp;
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@" + request);
            PerfilGuia perfilGuia =new PerfilGuia();

            perfilGuia.setNombreG(request.get("NombreG").toString());

            perfilGuia.setApellidoG(request.get("ApellidoG").toString());

            perfilGuia.setCorreoG(request.get("CorreoG").toString());

            perfilGuia.setFormacion(request.get("Formacion").toString());

            perfilGuia.setIdiomaM(request.get("Idioma_materno").toString());

            perfilGuia.setSegundoI(request.get("Segundo_idioma").toString());

            perfilGuia.setTelefonoG(Integer.parseInt(request.get("TelefonoG").toString()));
            perfilGuia.setLenguaSena(Boolean.parseBoolean(request.get("Lengua_sena").toString()));

            if(request.containsKey("Certificado") && request.get("Certificado") !=null){
                perfilGuia.setCertificado(request.get("Certificado").toString().getBytes());
            }

            perfilGuia.setLenguaSena(Boolean.parseBoolean(request.get("Lengua_sena").toString()));
            perfilGuia.setGenero(request.get("GeneroG").toString());



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
    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<PerfilGuia> perfilGuiaList = perfilGuiImp.findAll();
            response.put("status", "success");
            response.put("data", perfilGuiaList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            PerfilGuia perfilGuia = this.perfilGuiImp.findById(id);

            if (perfilGuia == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Perfil de guía no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            response.put("status", "success");
            response.put("data", perfilGuia);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            PerfilGuia perfilGuia = perfilGuiImp.findById(id);
            if (perfilGuia == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Perfil de guía no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            perfilGuiImp.delete(perfilGuia);
            response.put("status", HttpStatus.OK);
            response.put("message", "Perfil de guía eliminado correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            PerfilGuia perfilGuia = perfilGuiImp.findById(id);
            if (perfilGuia == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Perfil de guía no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (request.containsKey("NombreG")) {
                perfilGuia.setNombreG(request.get("NombreG").toString());
            }
            if (request.containsKey("ApellidoG")) {
                perfilGuia.setApellidoG(request.get("ApellidoG").toString());
            }
            if (request.containsKey("CorreoG")) {
                perfilGuia.setCorreoG(request.get("CorreoG").toString());
            }
            if (request.containsKey("Formacion")) {
                perfilGuia.setFormacion(request.get("Formacion").toString());
            }
            if (request.containsKey("Idioma_materno")) {
                perfilGuia.setIdiomaM(request.get("Idioma_materno").toString());
            }
            if (request.containsKey("Segundo_idioma")) {
                perfilGuia.setSegundoI(request.get("Segundo_idioma").toString());
            }
            if (request.containsKey("TelefonoG")) {
                perfilGuia.setTelefonoG(Integer.parseInt(request.get("TelefonoG").toString()));
            }
            if (request.containsKey("Certificado") && request.get("Certificado") != null) {
                perfilGuia.setCertificado(request.get("Certificado").toString().getBytes());
            }
            if (request.containsKey("Lengua_sena")) {
                perfilGuia.setLenguaSena(Boolean.parseBoolean(request.get("Lengua_sena").toString()));
            }
            if (request.containsKey("GeneroG")) {
                perfilGuia.setGenero(request.get("GeneroG").toString());
            }
            // Puedes agregar más campos para actualizar según sea necesario

            perfilGuiImp.update(perfilGuia);

            response.put("status", HttpStatus.OK);
            response.put("message", "Perfil de guía actualizado correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
