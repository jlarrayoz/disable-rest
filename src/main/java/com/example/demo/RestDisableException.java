package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN, reason = "El servicio rest esta deshabilitado")
public class RestDisableException extends Exception {

	private static final long serialVersionUID = 1L;

}
