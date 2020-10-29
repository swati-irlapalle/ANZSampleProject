package com.anz.account.exception;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {
  private static final String MESSAGE = "message";
  private static final String STATUS = "status";
  private final Logger logger = getLogger(getClass());

  @Autowired
  private ErrorAttributes errorAttributes;


  @ExceptionHandler(InvalidAccountException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ResponseBody
  public Map<String, Object> handleCustomExceptions(WebRequest request,
                                                    HttpServletResponse response, InvalidAccountException ex) {

    Map<String, Object> errorAttribute = this.errorAttributes.getErrorAttributes(request, false);
    errorAttribute.clear();
    errorAttribute.put(STATUS, BAD_REQUEST.value());
    errorAttribute.put(MESSAGE, ex.getMessage());
    return errorAttribute;
  }

  @ExceptionHandler(NoDataFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ResponseBody
  public Map<String, Object> handleCustomExceptions(WebRequest request,
                                                    HttpServletResponse response, NoDataFoundException ex) {

    Map<String, Object> errorAttribute = this.errorAttributes.getErrorAttributes(request, false);
    errorAttribute.clear();
    errorAttribute.put(STATUS, BAD_REQUEST.value());
    errorAttribute.put(MESSAGE, ex.getMessage());
    return errorAttribute;
  }


  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ResponseBody
  public Map<String, Object> handleCustomExceptions(WebRequest request,
                                                    HttpServletResponse response, MethodArgumentTypeMismatchException ex) {

    Map<String, Object> errorAttribute = this.errorAttributes.getErrorAttributes(request, false);
    errorAttribute.clear();
    errorAttribute.put(STATUS, BAD_REQUEST.value());
    errorAttribute.put(MESSAGE, "Invalid data received in request");
    return errorAttribute;
  }
}
