package com.dam.truequeworld.servicies;

import com.dam.truequeworld.models.Trueque;
import com.dam.truequeworld.repositories.TruequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TruqueService {
    @Autowired
    private TruequeRepository truequeRepository;
    @Autowired
    private ProductService productService;

    public List<Trueque> getTruquesByEstado(Integer estado){
        return truequeRepository.getTruequeByEstado(estado);
    }

    public Trueque saveTrueque(Trueque trueque){
        Trueque t =  truequeRepository.save(trueque);
        if(t!=null){
            productService.updateEstado(2,t.getProductoInteresado());
            productService.updateEstado(2,t.getProductoSolicitado());
        }
        return t;
    }
    public List<Trueque> getTruqueByUserEstado(Integer usuarioId, Integer estado){
        return truequeRepository.getTruqueByUserEstado(usuarioId,estado);
    }
}
