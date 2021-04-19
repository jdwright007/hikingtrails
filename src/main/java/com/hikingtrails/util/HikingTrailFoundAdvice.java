
package com.hikingtrails.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author John
 */

@ControllerAdvice
public class HikingTrailFoundAdvice {

  @ResponseBody
  @ExceptionHandler(HikingTrailFoundException.class)
  @ResponseStatus(HttpStatus.FOUND)
  String hikingTrailFoundHandler(HikingTrailFoundException ex) {
    return ex.getMessage();
  }
}
