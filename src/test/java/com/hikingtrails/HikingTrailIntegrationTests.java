
package com.hikingtrails;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;

import com.hikingtrails.domain.HikingTrail;


/**
 * Hiking Trails integration tests.
 * @author John
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HikingtrailsApplication.class,
                webEnvironment = WebEnvironment.RANDOM_PORT)
public class HikingTrailIntegrationTests {
  
    @LocalServerPort
    private int port;
 
    @Autowired
    private TestRestTemplate restTemplate;
  
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testGetAllHikingTrails() 
        throws JSONException {
        
        HttpEntity<String> entity = new HttpEntity<>(null, headers);        
        
        ResponseEntity<String> response = restTemplate.exchange(
                        createURLWithPort("/hikingtrails/getalltrails"),
                        HttpMethod.GET, entity, String.class);

        String expected = "[{\"id\":1,\"name\":\"Middle Fork\",\"distance\":5.0,\"difficulty\":\"easy\"},{\"id\":2,\"name\":\"Long Valley\",\"distance\":12.0,\"difficulty\":\"moderate\"}]";

        JSONAssert.assertEquals(expected, response.getBody(), false); 
        
    }    

    @Test
    public void testGetSingleHikingTrail() 
        throws JSONException {
        
        HttpEntity<String> entity = new HttpEntity<>(null, headers);        
        
        ResponseEntity<String> response = restTemplate.exchange(
                        createURLWithPort("/hikingtrails/gettrail/1"),
                        HttpMethod.GET, entity, String.class);

        String expected = "{\"id\":1,\"name\":\"Middle Fork\",\"distance\":5.0,\"difficulty\":\"easy\"}";
        
        JSONAssert.assertEquals(expected, response.getBody(), false);  
        
    }

    @Test
    public void testHikingTrailPostAndDelete() 
        throws JSONException {
        
        HikingTrail hikingTrail = new HikingTrail("Middle Fork", 10.0f,"moderate");
        hikingTrail.setId(3L);
        
        HttpEntity<HikingTrail> entity = new HttpEntity<>(hikingTrail, headers);        
        
        ResponseEntity<String> response = restTemplate.exchange(
                        createURLWithPort("/hikingtrails/newtrail"),
                        HttpMethod.POST, entity, String.class);

        String expected = "{\"id\": 3,\"name\":\"Middle Fork\",\"distance\":10.0,\"difficulty\": \"moderate\"}";

        JSONAssert.assertEquals(expected, response.getBody(), false);    
        
        HttpEntity<String> entity1 = new HttpEntity<>(null, headers);        
        
        ResponseEntity<String> response1 = restTemplate.exchange(
                        createURLWithPort("/hikingtrails/deletetrail/3"),
                        HttpMethod.DELETE, entity1, String.class);
        
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        
    }
    
    @Test
    public void testHikingTrailPut() 
        throws JSONException {
        
        HttpEntity<String> entity = new HttpEntity<>(null, headers);        
        
        ResponseEntity<String> response = restTemplate.exchange(
                        createURLWithPort("/hikingtrails/gettrail/1"),
                        HttpMethod.GET, entity, String.class);   
        
        HikingTrail hikingTrail = new HikingTrail("Snow Mountain", 10.0f, "difficult");
        hikingTrail.setId(1L);
         
        HttpEntity<HikingTrail> entity1 = new HttpEntity<>(hikingTrail, headers);        
        
        ResponseEntity<String> response1 = restTemplate.exchange(
                        createURLWithPort("/hikingtrails/replacetrail/1"),
                        HttpMethod.PUT, entity1, String.class);

        String expected = "{\"id\":1,\"name\":\"Snow Mountain\",\"distance\":10.0,\"difficulty\":\"difficult\"}";

        assertEquals(HttpStatus.CREATED, response1.getStatusCode());
        JSONAssert.assertEquals(expected, response1.getBody(), false);  

        JSONObject jobject = new JSONObject(response.getBody());
        
        HikingTrail hikingTrail2 = new HikingTrail(jobject.getString("name"),
                                                   jobject.getLong("distance"),
                                                   jobject.getString("difficulty"));
        hikingTrail2.setId(jobject.getLong("id"));
        HttpEntity<HikingTrail> entity2 = new HttpEntity<>(hikingTrail2, headers); 
        
        ResponseEntity<String> response2 = restTemplate.exchange(
                        createURLWithPort("/hikingtrails/replacetrail/1"),
                        HttpMethod.PUT, entity2, String.class);    
        
        assertEquals(HttpStatus.CREATED, response2.getStatusCode());       
        
    }    
    
    @Test
    public void testHikingTrailGetException() {
        
        HttpEntity<String> entity = new HttpEntity<>(null, headers);        
        
        int maxrec = Integer.MAX_VALUE;
        ResponseEntity<String> response = restTemplate.exchange(
                        createURLWithPort("/hikingtrails/gettrail/" + maxrec),
                        HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());   
                
    }
    
    @Test
    public void testHikingTrailPostException() {
        
        HikingTrail hikingTrail = new HikingTrail("Middle Fork", 10.0f,"moderate");
        hikingTrail.setId(1L);
        
        HttpEntity<HikingTrail> entity = new HttpEntity<>(hikingTrail, headers);        
        
        ResponseEntity<String> response = restTemplate.exchange(
                        createURLWithPort("/hikingtrails/newtrail"),
                        HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());          
        
    }    

    @Test
    public void testHikingTrailDeleteException() {   
        
        HttpEntity<String> entity = new HttpEntity<>(null, headers);        
        
        int maxrec = Integer.MAX_VALUE;
        
        ResponseEntity<String> response1 = restTemplate.exchange(
                        createURLWithPort("/hikingtrails/deletetrail/" + maxrec),
                        HttpMethod.DELETE, entity, String.class);
        
        assertEquals(HttpStatus.NOT_FOUND, response1.getStatusCode());
        
    }
    
    @Test
    public void testHikingTrailPutException() {
        
        HikingTrail hikingTrail = new HikingTrail("Snow Mountain", 10.0f, "difficult");
                
        long maxrec = Integer.MAX_VALUE;
        hikingTrail.setId(maxrec);
         
        HttpEntity<HikingTrail> entity = new HttpEntity<>(hikingTrail, headers);        
        
        ResponseEntity<String> response = restTemplate.exchange(
                        createURLWithPort("/hikingtrails/replacetrail/" + maxrec),
                        HttpMethod.PUT, entity, String.class);;    
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());       
        
    }    
        
    private String createURLWithPort(String uri) {
        
        return "http://localhost:" + port + uri;
            
    }    
}
