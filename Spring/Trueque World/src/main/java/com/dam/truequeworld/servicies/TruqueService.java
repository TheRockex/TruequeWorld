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

    public List<Trueque> getTruquesByEstado(Integer estado){
        return truequeRepository.getTruequeByEstado(estado);
    }

    public Trueque saveTrueque(Trueque trueque){
        return truequeRepository.save(trueque);
    }
}
