package com.dam.truequeworld.controlers;

import com.dam.truequeworld.models.Trueque;
import com.dam.truequeworld.servicies.TruqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trueque")
public class TruequeController {
    @Autowired
    TruqueService truequeService;

    @GetMapping("/estado/{estado}")
    public List<Trueque> getTruequesByEstado(@PathVariable("estado")Integer estado){
        return truequeService.getTruquesByEstado(estado);
    }

    @PostMapping("/save")
    public Trueque saveTrueque(@RequestBody Trueque trueque){
        return truequeService.saveTrueque(trueque);
    }

    @GetMapping("/solicitud/{userId}/{estado}")
    public List<Trueque> getTruqueByUserEstado(@PathVariable Integer userId, @PathVariable Integer estado){
        return truequeService.getTruqueByUserEstado(userId,estado);
    }
}
