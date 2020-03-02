package com.legensity.b2bmall.exception;

import com.legensity.b2bmall.result.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.apache.shiro.authc.AuthenticationException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * DefaultExceptionHandler:自定义异常处理器
 *
 * @author wutao
 * @date: 2020/01/23
 */
@Slf4j
@ControllerAdvice  //不指定包默认加了@Controller和@RestController都能控制
//@ControllerAdvice(basePackages ="com.example.demo.controller")
public class DefaultExceptionHandler {

    /**
     * 异常统一自定义处理
     */
    @ExceptionHandler({MyException.class})
    @ResponseBody
    public ResponseData MyException(MyException e) {
        ResponseData responseData = new ResponseData<>();
        responseData.setCode(500);
        responseData.setMsg(e.getMessage());
        responseData.setData(null);
        e.printStackTrace();
        return responseData;
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseData exceptionHandler(MethodArgumentNotValidException e) {
        ResponseData responseData = new ResponseData<>();
        responseData.setCode(500);
        responseData.setMsg(e.getBindingResult().getFieldError().getField() + e.getBindingResult().getFieldError().getDefaultMessage());
        responseData.setData(null);
        return responseData;
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ResponseData validationExceptionHandler(BindException e) {
        ResponseData responseData = new ResponseData<>();
        responseData.setCode(500);
        responseData.setMsg(e.getBindingResult().getFieldError().getField() + e.getBindingResult().getFieldError().getDefaultMessage());
        responseData.setData(null);
        return responseData;
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ResponseData ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        ResponseData responseData = new ResponseData<>();
        responseData.setCode(500);
        responseData.setMsg(msgList.get(0));
        responseData.setData(null);
        return responseData;
    }

    /**
     * 业务异常处理
     *
     * @param e
     * @return ErrorInfo
     */
    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseData BusinessExceptionHandler(AuthenticationException e) {
        ResponseData responseData = new ResponseData<>();
        responseData.setCode(401);
        responseData.setMsg("没有权限");
        responseData.setData(null);
        return responseData;
    }

    /**
     * 异常统一处理(最后的异常处理)
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseData allException(Exception e) {
        ResponseData responseData = new ResponseData<>();
        responseData.setCode(500);
        responseData.setMsg("系统异常:" + e.getMessage());
        responseData.setData(null);
        e.printStackTrace();
        return responseData;
    }


}
