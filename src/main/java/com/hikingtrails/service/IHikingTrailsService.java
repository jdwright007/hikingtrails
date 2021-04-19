
package com.hikingtrails.service;

import java.util.List;
import java.util.Optional;

import com.hikingtrails.domain.HikingTrail;


/**
 *
 * @author John
 */
public interface IHikingTrailsService {
    
    List<HikingTrail> findAllHikingTrails();
    
    Optional<HikingTrail> findHikingTrail(long id);
    HikingTrail addHikingTrail(HikingTrail hikingTrail);
    void deleteHikingTrail(long id);
    HikingTrail modifyHikingTrail(HikingTrail hikingTrail, long id);
    
}
