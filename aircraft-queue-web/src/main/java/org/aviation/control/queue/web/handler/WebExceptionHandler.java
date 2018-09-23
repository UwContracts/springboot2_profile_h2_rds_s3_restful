package org.aviation.control.queue.web.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebExceptionHandler.class);

	@ExceptionHandler(Throwable.class)
	public String handleException(Throwable ex, HttpServletResponse response) throws IOException {
		LOGGER.error("Unexpected error occurred", ex);

        return "error";
	}

}
