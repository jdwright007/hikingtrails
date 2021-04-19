
package com.hikingtrails;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import com.hikingtrails.domain.HikingTrail;


/**
 * Hiking Trails application tests.
 * @author John
 */
@SpringBootTest
class HikingtrailsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testHikingTrailGettersAndSetters() {
        
        HikingTrail hikingTrail = new HikingTrail("One", 1.0f,"easy");
        
        assertEquals("One",hikingTrail.getName(), "New object exception");
        assertEquals(1.0f,hikingTrail.getDistance(), "New object exception");
        assertEquals("easy",hikingTrail.getDifficulty(), "New object exception");        
          
    }
}
