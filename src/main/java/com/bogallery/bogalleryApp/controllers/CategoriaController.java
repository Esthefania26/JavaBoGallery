package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Categoria;
import com.bogallery.bogalleryApp.service.imp.CategoriaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/categoria/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
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


    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Categoria> categoriaList = this.categoriaImp.findAll();
            response.put("status", "success");
            response.put("data", categoriaList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Categoria categoria = this.categoriaImp.findById(id);

            if (categoria == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Categoría no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            response.put("status", "success");
            response.put("data", categoria);
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
            Categoria categoria = this.categoriaImp.findById(id);
            categoriaImp.delete(categoria);
            response.put("status", "success");
            response.put("message", "Categoría eliminada correctamente");
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
            Categoria categoria = this.categoriaImp.findById(id);
            if (categoria == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Categoría no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (request.containsKey("descripcionC")) {
                categoria.setDescripcionC(request.get("descripcionC").toString());
            }
            this.categoriaImp.update(categoria);
            response.put("status", "success");
            response.put("data", "Categoría actualizada correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
