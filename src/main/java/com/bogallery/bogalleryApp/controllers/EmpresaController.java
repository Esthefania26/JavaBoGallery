package com.bogallery.bogalleryApp.controllers;
import com.bogallery.bogalleryApp.entities.Empresa;
import com.bogallery.bogalleryApp.entities.Rol;
import com.bogallery.bogalleryApp.service.imp.EmpresaImp;
import com.bogallery.bogalleryApp.service.imp.RolImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/empresa/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class EmpresaController {

    @Autowired
    EmpresaImp empresaImp;

    @Autowired
    private RolImp rolImp;



    @PostMapping("create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody Map<String,Object> request){
        Map<String,Object> response=new HashMap<>();



        try {
            System.out.println("Contenido del mapa request: " + request);
            System.out.println("@@@@"+request);
            Empresa empresa = new Empresa();



            empresa.setNit(Long.parseLong(request.get("Nit_empresa").toString()));
            empresa.setNombreEm(request.get("Nombre_em").toString());
            empresa.setBarrioEm(request.get("Barrio_em").toString());
            empresa.setCorreoEm(request.get("Correo_em").toString());
            empresa.setDireccionEm(request.get("Direccion_em").toString());
            empresa.setEstadoEm(request.get("Estado_em").toString().charAt(0));

            String fechaRegistro = request.get("Fecha_registroEm").toString(); // Obtener la fecha como una cadena
            LocalDateTime fechaRegistroEm = LocalDateTime.parse(fechaRegistro + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            empresa.setFecha_registroEm(fechaRegistroEm); // Establecer la fecha de registro en el objeto Empresa

            empresa.setLocalidadEm(request.get("Localidad_em").toString());
            empresa.setRazon(request.get("Razon_social").toString());
            empresa.setTelefono_em(request.get("Telefono_em").hashCode());
            empresa.setRut(request.get("Rut").hashCode());

            Rol rol = rolImp.findById(Long.parseLong(request.get("Id_rol").toString()));

            empresa.setRol(rol);

           this.empresaImp.create(empresa);
            response.put("status","success");
            response.put("data","Registro Exitoso");
        }catch (Exception e){
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("error",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Empresa> empresaList = empresaImp.findAll();
            response.put("status", "success");
            response.put("data", empresaList);
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
            Empresa empresa = empresaImp.findById(id);
            if (empresa == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Empresa no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("status", "success");
            response.put("data", empresa);
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
            Empresa empresa = empresaImp.findById(id);
            if (empresa == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Empresa no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            empresaImp.delete(empresa);
            response.put("status", HttpStatus.OK);
            response.put("message", "Empresa eliminada correctamente");
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
            Empresa empresa = empresaImp.findById(id);
            if (empresa == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Empresa no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            empresa.setNit(Long.parseLong(request.get("Nit_empresa").toString()));
            empresa.setNombreEm(request.get("Nombre_em").toString());
            empresa.setBarrioEm(request.get("Barrio_em").toString());
            empresa.setCorreoEm(request.get("Correo_em").toString());
            empresa.setDireccionEm(request.get("Direccion_em").toString());
            empresa.setEstadoEm(request.get("Estado_em").toString().charAt(0));

            String fechaRegistro = request.get("Fecha_registroEm").toString(); // Obtener la fecha como una cadena
            LocalDateTime fechaRegistroEm = LocalDateTime.parse(fechaRegistro + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            empresa.setFecha_registroEm(fechaRegistroEm); // Establecer la fecha de registro en el objeto Empresa

            empresa.setLocalidadEm(request.get("Localidad_em").toString());
            empresa.setRazon(request.get("Razon_social").toString());
            empresa.setTelefono_em(request.get("Telefono_em").hashCode());
            empresa.setRut(request.get("Rut").hashCode());

            empresaImp.update(empresa);

            response.put("status", HttpStatus.OK);
            response.put("message", "Empresa actualizada correctamentee");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
