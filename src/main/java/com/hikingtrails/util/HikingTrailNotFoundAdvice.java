
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
public class HikingTrailNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(HikingTrailNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String hikingTrailNotFoundHandler(HikingTrailNotFoundException ex) {
    return ex.getMessage();
  }
}
