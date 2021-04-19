
package com.hikingtrails.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Hiking Trails repository.
 * @author John
 */
public interface HikingTrailsRepository extends JpaRepository<HikingTrail, Long> {
    
    HikingTrail findByName(String name);
    
}
