package com.bogallery.bogalleryApp.controllers;
import com.bogallery.bogalleryApp.entities.Empresa;
import com.bogallery.bogalleryApp.service.imp.EmpresaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/empresa/")
@CrossOrigin("*")
public class EmpresaController {

    @Autowired
    EmpresaImp empresaImp;

    @PostMapping("create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody Map<String,Object> request){
        Map<String,Object> response=new HashMap<>();

        try {
            System.out.println("@@@@"+request);
            Empresa empresa = new Empresa();
empresa.setNombreEm(request.get("nombreEm").toString());
empresa.setBarrioEm(request.get("barrioEm").toString());
empresa.setCorreoEm(request.get("correoEm").toString());
empresa.setDireccionEm(request.get("direccionEm").toString());
//empresa.setNit(request.get("nit").hashCode())
// empresa.setEstadoEm(request.get("estadoEm").toString());
 DateTimeFormatter formatterFechaL = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta el formato según tus necesidades
empresa.setFecha_registroEm(LocalDateTime.parse(request.get("Fecha_registroEM").toString(), formatterFechaL));
empresa.setLocalidadEm(request.get("localidadEmp").toString());
empresa.setRazon(request.get("razon").toString());
empresa.setTelefono_em(request.get("telefono").hashCode());
empresa.setRut(request.get("rut").hashCode());

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
