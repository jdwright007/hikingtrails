
package com.hikingtrails.util;

/**
 *
 * @author John
 */
public class HikingTrailNotFoundException extends RuntimeException{
    
    public HikingTrailNotFoundException(Long id) {
        
        super("Could not find hiking trail: " + id);
        
    }
    
}
