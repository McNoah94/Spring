package com.codingdojo.dojos.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import com.codingdojo.dojos.models.Dojo;
import com.codingdojo.dojos.models.Ninja;
import com.codingdojo.dojos.repositories.DojoRepository;
import com.codingdojo.dojos.repositories.NinjaRepository;
@Service
public class DojoNinjaService {
    // adding the Dojo repository as a dependency
    private final DojoRepository dojoRepository;
    private final NinjaRepository ninjaRepository;
    
    public DojoNinjaService(DojoRepository dojoRepository, NinjaRepository ninjaRepository) {
        this.dojoRepository = dojoRepository;
        this.ninjaRepository = ninjaRepository;
    }
    // returns all the Dojos
    public List<Dojo> allDojos() {
        return dojoRepository.findAll();
    }
    // creates a Dojo
    public Dojo createDojo(Dojo b) {
        return dojoRepository.save(b);
    }
    // retrieves a Dojo
    public Dojo findDojo(Long id) {
        Optional<Dojo> optionalDojo = dojoRepository.findById(id);
        if(optionalDojo.isPresent()) {
            return optionalDojo.get();
        } else {
            return null;
        }
    }
	public Ninja createNinja(@Valid Ninja ninja) {
		return ninjaRepository.save(ninja);
	}
}