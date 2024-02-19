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

    @GetMapping("/favorite")
    public List<Mensaje> getChats(){
        return mensajeService.getAllMensajes();
    }

    @GetMapping("/id/{id}")
    public Mensaje getChatById(@PathVariable Integer id){
        return mensajeService.getMensajeById(id);
    }

    @PostMapping("/save")
    public Mensaje insertChat(@RequestBody Mensaje mensaje){
        return mensajeService.saveMensaje(mensaje);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteChatById(@PathVariable Integer id){
        return mensajeService.deleteMensajeById(id);
    }

    @CrossOrigin
    @RequestMapping("/conectarse")
    public SseEmitter suscribe(@RequestParam String userId){
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        sendInitEvent(sseEmitter);
        emitters.put(userId, sseEmitter);

        sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));
        sseEmitter.onTimeout(() -> emitters.remove(sseEmitter));
        sseEmitter.onError((e) -> emitters.remove(sseEmitter));

        return sseEmitter;
    }
    // method for dispatching events to a client
    @PostMapping("/enviarMensaje")
    public void dispatchEventToClients(@RequestParam String freshNews, String userId){
        SseEmitter sseEmitter = emitters.get(userId);
        if(sseEmitter != null){
            try {
                sseEmitter.send(SseEmitter.event().name("latestNews").data(freshNews));
            } catch (IOException e) {
                emitters.remove(sseEmitter);
            }
        }
    }

    private void sendInitEvent(SseEmitter sseEmitter){
        try {
            sseEmitter.send(SseEmitter.event().name("Init"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
