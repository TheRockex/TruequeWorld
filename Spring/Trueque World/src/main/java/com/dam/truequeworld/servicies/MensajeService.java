package com.dam.truequeworld.servicies;

import com.dam.truequeworld.models.Chat;
import com.dam.truequeworld.models.Mensaje;
import com.dam.truequeworld.repositories.ChatRepository;
import com.dam.truequeworld.repositories.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeService {
    @Autowired
    private MensajeRepository mensajeRepository;

    public List<Mensaje> getAllMensajes(){
        return mensajeRepository.findAll();
    }

    public Mensaje getMensajeById(Integer id){
        return mensajeRepository.findById(id).orElse(null);
    }

    public Mensaje saveMensaje(Mensaje mensaje){
        return mensajeRepository.save(mensaje);
    }

    public boolean deleteMensajeById(Integer id){
        mensajeRepository.deleteById(id);
        return mensajeRepository.findById(id).isEmpty();
    }
}
