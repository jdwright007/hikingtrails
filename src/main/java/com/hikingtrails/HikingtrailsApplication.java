package com.hikingtrails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Hiking Trails Application
 * @author John
 */

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.hikingtrails.domain")
public class HikingtrailsApplication extends WebMvcConfigurerAdapter {
       
    public static final Logger logger = LoggerFactory.getLogger(HikingtrailsApplication.class);    

    public static void main(String[] args) {
            SpringApplication.run(HikingtrailsApplication.class, args);
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
      registry.addRedirectViewController("/hikingtrails", "/hikingrails");
    }    

}
