
package com.hikingtrails.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.hikingtrails.domain.HikingTrail;
import com.hikingtrails.domain.HikingTrailsRepository;
import com.hikingtrails.util.HikingTrailNotFoundException;
import com.hikingtrails.util.HikingTrailFoundException;


/**
 * Hiking Trail services.
 * @author John
 */
@Service
public class HikingTrailsService implements IHikingTrailsService {
    
    private final HikingTrailsRepository hikingTrailsRepository;

    
    public HikingTrailsService(HikingTrailsRepository hikingTrailsRepository) {

        this.hikingTrailsRepository = hikingTrailsRepository;
    }

    public List<HikingTrail> findAllHikingTrails() {

        List<HikingTrail> hikingTrailList = (List<HikingTrail>) hikingTrailsRepository.findAll();

        return hikingTrailList;
        
    }  

    public Optional<HikingTrail> findHikingTrail(long id) {

        Optional<HikingTrail> hikingTrail = hikingTrailsRepository.findById(id);

        return hikingTrail;

    }

    public HikingTrail addHikingTrail(HikingTrail hikingTrail) {
        
        if (hikingTrailsRepository.findById(hikingTrail.getId()).isPresent()) {
            throw new HikingTrailFoundException(hikingTrail.getId());
        }

        HikingTrail newHikingTrail = hikingTrailsRepository.save(hikingTrail);

        return newHikingTrail;
        
    }  

    public void deleteHikingTrail(long id) {

        hikingTrailsRepository.findById(id)
            .orElseThrow(() -> new HikingTrailNotFoundException(id) );

        hikingTrailsRepository.deleteById(id);

    }

    public HikingTrail modifyHikingTrail(HikingTrail hikingTrail, long id) {

        hikingTrailsRepository.findById(id)
            .map(trail -> {
                trail.setName(hikingTrail.getName());
                trail.setDistance(hikingTrail.getDistance());
                trail.setDifficulty(hikingTrail.getDifficulty());
                return hikingTrailsRepository.save(trail);
            })
            .orElseGet( () -> {
                hikingTrail.setId(id);
                return hikingTrailsRepository.save(hikingTrail);
            });    

        Optional<HikingTrail> hikingTrail1 = hikingTrailsRepository.findById(id);
        
        return hikingTrail1.get();

    }
    
}
