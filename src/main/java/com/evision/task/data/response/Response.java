
package com.evision.task.data.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Mohamed Ramadan
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

	private Status status;
	private T payload;
	private Object errors;
	private Object metadata;
	private String message;

	public static <T> Response<T> badRequest() {
		Response<T> response = new Response<>();
		response.setStatus(Status.BAD_REQUEST);
		return response;
	}

	public static <T> Response<T> ok() {
		Response<T> response = new Response<>();
		response.setStatus(Status.OK);
		return response;
	}

	public static <T> Response<T> created() {
		Response<T> response = new Response<>();
		response.setStatus(Status.CEREATED);
		return response;
	}

	public static <T> Response<T> unauthorized() {
		Response<T> response = new Response<>();
		response.setStatus(Status.UNAUTHORIZED);
		return response;
	}

	public static <T> Response<T> notFound() {
		Response<T> response = new Response<>();
		response.setStatus(Status.NOT_FOUND);
		return response;
	}

	public static <T> Response<T> internalError() {
		Response<T> response = new Response<>();
		response.setStatus(Status.INTERNAL_ERROR);
		return response;
	}

	public void addErrorMsgToResponse(String errorMsg, Exception ex) {
		ResponseError error = new ResponseError().setDetails(errorMsg).setMessage(ex.toString())
				.setTimestamp(new Date());
		setErrors(error);
	}

	public enum Status {
		OK, BAD_REQUEST, CEREATED, UNAUTHORIZED, NOT_FOUND, INTERNAL_ERROR
	}

	@Getter
	@Accessors(chain = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@AllArgsConstructor
	public static class PageMetadata {
		private final int size;
		private final long totalElements;
		private final int totalPages;
		private final int number;

	}

}
