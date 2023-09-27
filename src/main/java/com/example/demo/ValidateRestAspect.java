package com.example.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletResponse;

@Aspect
@Component
public class ValidateRestAspect {
	
	@Autowired
    private HttpServletResponse httpServletResponse;

  @Pointcut("@annotation(com.example.demo.ValidateRest)")
  public void callMeValidateRest() {}

  @Around("callMeValidateRest()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    
    if(ConfigRest.enableRest()) {
        return joinPoint.proceed();
    }
    else {
    	//Alternativa 1: lanzo excepción
    	//throw new RestDisableException();
    	
    	//Alternativa 2: escribo directamente en el response
    	httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "El servicio rest esta deshabilitado response");
    	return null;
    }
  }
}