package com.dam.truequeworld.controlers;

import com.dam.truequeworld.models.Mensaje;
import com.dam.truequeworld.servicies.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mensaje")
public class MensajeController {
    public Map<String, SseEmitter> emitters = new HashMap<>();
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

    @DeleteMapping("/delete/{id}")
    public Boolean deleteMensajeById(@PathVariable Integer id){
        return mensajeService.deleteMensajeById(id);
    }
    @PostMapping("/enviar/{mensaje}/{userId}")
    public Boolean dispatchEventToClients(@PathVariable String mensaje,@PathVariable String userId){
        System.out.println("entroooo");
        SseEmitter sseEmitter = emitters.get(userId);
        if(sseEmitter != null){
            try {
                sseEmitter.send(SseEmitter.event().name("latestNews").data(mensaje));
                System.out.println("entro");
            } catch (IOException e) {
                emitters.remove(sseEmitter);
                System.out.println("ERROR");
            }
        }else {
            System.out.println("conexion es nula");
        }
        return true;
    }

    @PostMapping("/conectarse/{userId}")
    public Boolean suscribe(@PathVariable String userId){
        System.out.println("USUARIO CONECTADO: "+ userId);
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        sendInitEvent(sseEmitter);
        emitters.put(userId, sseEmitter);

        sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));
        sseEmitter.onTimeout(() -> emitters.remove(sseEmitter));
        sseEmitter.onError((e) -> emitters.remove(sseEmitter));
        return true;
    }
    // method for dispatching events to a client


    private void sendInitEvent(SseEmitter sseEmitter){
        try {
            sseEmitter.send(SseEmitter.event().name("Init"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
