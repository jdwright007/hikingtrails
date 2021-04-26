 
package com.hikingtrails.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Data;


/**
 * Hiking Trail database record.
 * @author John
 */
@Entity
@Getter @Setter @NoArgsConstructor @Data
public class HikingTrail implements Serializable {
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @NotNull
    private String name;
    
    @NotNull
    private float distance;
    
    private String difficulty;
    
    public HikingTrail(String name, float distance, String difficulty) {
        this.name = name;
        this.distance = distance;
        this.difficulty = difficulty;
    }
       
}
