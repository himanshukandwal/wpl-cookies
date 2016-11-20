package edu.utdallas.wpl.cookies.front.end.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingController {

  @ResponseStatus(value=HttpStatus.OK)
  @ExceptionHandler(Exception.class)
  public Map<String, Object> genericException() {
	  System.out.println("in ExceptionHandlingController  !");
      Map<String, Object> answer = new HashMap<>();
      answer.put("status", "failure");
      return answer;
  }

}