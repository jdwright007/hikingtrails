
package com.hikingtrails.controller;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;

import com.hikingtrails.service.IHikingTrailsService;
import com.hikingtrails.domain.HikingTrail;
import com.hikingtrails.util.CustomErrorType;
import com.hikingtrails.util.HikingTrailNotFoundException;

/**
 * Hiking Trails controller
 * @author John
 */

@RestController
@RequestMapping("/hikingtrails")
public class HikingTrailsController {
    
    public static final Logger logger = LoggerFactory.getLogger(HikingTrailsController.class);
    
    @Autowired
    private IHikingTrailsService service;
    
    /**
    * Return list of hiking trails.
    * @param 
    * @return List<hikingTrail>
    */    
    @GetMapping("/getalltrails")
    public ResponseEntity<List<HikingTrail>> findAllTrails(){
        
        logger.info("Get current list of all trails");
        
        List<HikingTrail> hikingTrailList = service.findAllHikingTrails();
        
        if (hikingTrailList.isEmpty() ) {
            
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(hikingTrailList, HttpStatus.OK);
    } 
    
    /**
    * Return single hiking trails.
    * @param id
    * @return hikingTrail
    */
    @GetMapping("/gettrail/{id}")
    public ResponseEntity<HikingTrail> findTrailById(@PathVariable("id") long id){
        
        logger.info("Get a single hiking trail with id: " + id);
        
        Optional<HikingTrail> hikingTrail = service.findHikingTrail(id);
        
        if (hikingTrail.isEmpty()) {
            
            throw new HikingTrailNotFoundException(id);
        }
        
        return new ResponseEntity<> (hikingTrail.get(), HttpStatus.OK);
    }
    
    /**
    * Add a hiking trail to the database of hiking trails.
    * @param hikingTrail
    * @return hikingTrail
    */
    @PostMapping(value = "/newtrail")
    public ResponseEntity <HikingTrail> addHikingTrail(@RequestBody HikingTrail hikingTrail) {
        
        logger.info("Add new hiking trail ");

        HikingTrail newHikingTrail = service.addHikingTrail(hikingTrail);
        
        return new ResponseEntity<> (newHikingTrail, HttpStatus.CREATED);
    }

    /**
    * Delete a hiking trail given id.
    * @param id
    * @return
    */
    @DeleteMapping(value = "/deletetrail/{id}")
    public ResponseEntity<String> deleteTrail(@PathVariable Long id) {
        
        logger.info("Delete hiking trail with id: " + id);

         service.deleteHikingTrail(id);

         return new ResponseEntity<> (HttpStatus.OK);
    }   
    
    /**
     * Replace a hiking trail given hiking trail object and id.
     * @param hikingTrail
     * @param id
     * @return hikingTrail
     */
    @PutMapping("replacetrail/{id}")
    public ResponseEntity<HikingTrail> replaceTrail(@RequestBody HikingTrail hikingTrail,
                                                     @PathVariable long id) {
 
        logger.info("Replace hiking trail with id: " + id);
        
        Optional<HikingTrail> hikingTrail1 = service.findHikingTrail(id);
        
        if (hikingTrail1.isEmpty()) {
            
            throw new HikingTrailNotFoundException(id);
        }        
                
        HikingTrail trail = service.modifyHikingTrail(hikingTrail, id);
        
        return new ResponseEntity<> (trail, HttpStatus.CREATED);
    }
}
