package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Usuario;
import com.bogallery.bogalleryApp.service.imp.UsuarioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/usuario/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioImp usuarioImp;
    @PostMapping("create")

    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Objects> request){
Map<String,Object> response=new HashMap<>();
        try{

            System.out.println("@@@"+request);
            Usuario usuario=new Usuario();
            //usuario.setId(Long.parseLong(request.get("Id_usu").toString()));
            usuario.setNombre(request.get("Nombre_usu").toString());
            usuario.setApellido(request.get("Apellido_uso").toString());
            usuario.setEdad(Integer.parseInt(request.get("Edad").toString()));
            usuario.setDireccion(request.get("Direccion_usu").toString());
           /* DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta el formato según tus necesidades
            usuario.setFecha(LocalDate.parse(request.get("Fecha_usu").toString(), formatter));
*/          usuario.setTelefono(Integer.parseInt(request.get("Telefono_usu").toString()));
            usuario.setCoreo(request.get("Correo_usu").toString());
            usuario.setPasswaord(request.get("Password_usu").toString());
            usuario.setPrimerI(request.get("Primer_idioma").toString());
            usuario.setSegundoI(request.get("Segundo_idioma").toString());
            //GeneroEnum genero = GeneroEnum.valueOf(request.get("Genero_usu").toString());
            //usuario.setGenero(request.get("Genero_usu").toString());
            this.usuarioImp.create(usuario);

            response.put("status", "success");
            response.put("data", "Registro Exitoso");

        }catch (Exception e)
        {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll(){
    Map<String,Object> response=new HashMap<>();


        try {
            List<Usuario> usuarioList = this.usuarioImp.findAll();
            response.put("status", "succes");
            response.put("data", usuarioList);

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
