package com.newdon.handler;

import com.newdon.base.BaseException;
import com.newdon.constants.CommonConstants;
import lombok.extern.log4j.Log4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author Rlserim
 * @create 2018/1/12 9:32
 * @desc
 **/
@RestControllerAdvice
@Log4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public void baseExceptionHandler(HttpServletResponse response, BaseException ex) throws IOException {
        log.error(ex.getMsg(), ex);
        response.sendError(ex.getStatus(), ex.getMsg());
    }

    @ExceptionHandler(BindException.class)
    public void validHandler(HttpServletResponse response, BindException ex) throws IOException {
        String message = "";
        List<ObjectError> allErrors = ex.getAllErrors();
        for (ObjectError objectError : allErrors) {
            message += objectError.getDefaultMessage() + ",";
            break;
        }
        message = message.substring(0, message.length() - 1);
        log.error(message, ex);
        response.sendError(CommonConstants.EX_REQUEST_PARAMETER_ILLEGAL_CODE, message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void validHandler(HttpServletResponse response, ConstraintViolationException ex) throws IOException {
        String message = "";
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            message += violation.getMessage() + ",";
        }
        message = message.substring(0, message.length() - 1);
        log.error(message, ex);
        response.sendError(CommonConstants.EX_REQUEST_PARAMETER_ILLEGAL_CODE, message);
    }

    @ExceptionHandler(Exception.class)
    public void otherExceptionHandler(HttpServletResponse response, Exception ex) throws IOException {
        log.error(ex.getMessage(), ex);
        response.sendError(CommonConstants.EX_OTHER_CODE, ex.getMessage());
    }
}
