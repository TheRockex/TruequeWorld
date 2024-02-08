package com.dam.truequeworld.controlers;

import com.dam.truequeworld.models.Chat;
import com.dam.truequeworld.models.Mensaje;
import com.dam.truequeworld.servicies.ChatService;
import com.dam.truequeworld.servicies.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensaje")
public class MensajeController {
    @Autowired
    private MensajeService mensajeService;

    @GetMapping("/mensajes")
    public List<Mensaje> getMensajes(){
        return mensajeService.getAllMensajes();
    }

    @GetMapping("/id/{id}")
    public Mensaje getMensajeById(@PathVariable Integer id){
        return mensajeService.getMensajeById(id);
    }

    @PostMapping("/save")
    public Mensaje insertMensaje(@RequestBody Mensaje mensaje){
        return mensajeService.saveMensaje(mensaje);
    }
    @PutMapping("/update")
    public Mensaje updateMensaje(@RequestBody Mensaje mensaje){
        return mensajeService.saveMensaje(mensaje);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteMensajeById(@PathVariable Integer id){
        return mensajeService.deleteMensajeById(id);
    }
}

