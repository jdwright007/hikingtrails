
package com.hikingtrails.util;

/**
 *
 * @author John
 */
public class HikingTrailFoundException extends RuntimeException{
    
    public HikingTrailFoundException(Long id) {
        
        super("Existing hiking trail found with id: " + id);
        
    }
    
}
