/**
 * 
 */
package com.evision.task.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.evision.task.data.response.Response;
import com.evision.task.exception.ExceptionFactory;

/**
 * @author Mohamed Ramadan
 *
 */

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ExceptionFactory.EntityNotFoundException.class)
	public final Response<Object> handleInvitationCreationException(Exception ex, WebRequest request) {
		Response<Object> response = Response.notFound();
		response.addErrorMsgToResponse(ex.getMessage(), ex);
		return response;
	}

	@ExceptionHandler(AccessDeniedException.class)
	public final Response<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		Response<Object> response = Response.unauthorized();
		response.addErrorMsgToResponse(ex.getMessage(), ex);

		return response;
	}

	@ExceptionHandler(Exception.class)
	public final Response<Object> handleExceptions(Exception ex, WebRequest request) {
		Response<Object> response = Response.internalError();
		response.addErrorMsgToResponse(ex.getMessage(), ex);

		return response;
	}
}
