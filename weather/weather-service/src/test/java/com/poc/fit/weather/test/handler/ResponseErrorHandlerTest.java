package com.poc.fit.weather.test.handler;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import com.fit.services.weather.error.WeatherException;
import com.fit.services.weather.handler.ErrorHandler;

public class ResponseErrorHandlerTest {
	private ErrorHandler responseErrorHandler;

	private ClientHttpResponse clientHttpResponse;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() throws Exception {
		responseErrorHandler = new ErrorHandler();
	}

	@After
	public void tearDown() {
		responseErrorHandler = null;
	}

	@Test
	public void verifyCreateOwmResponseErrorHandlerObject() {
		assertNotNull(responseErrorHandler);
	}

	@Test
	public void verifyHandleError() throws Exception {
		String exceptionMessage = "Bad Request";
		exception.expect(WeatherException.class);
		exception.expectMessage(containsString(exceptionMessage));
		clientHttpResponse = mock(ClientHttpResponse.class);

		when(clientHttpResponse.getStatusText()).thenReturn(exceptionMessage);
		when(clientHttpResponse.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);

		responseErrorHandler.handleError(clientHttpResponse);
	}
}