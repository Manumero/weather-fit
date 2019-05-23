package com.fit.services.weather.service.utils;

import java.util.List;
import java.util.Optional;

public class ValidationUtils {
	
	private ValidationUtils() {
		throw new IllegalStateException("ValidationUtils class");
	}

	public static boolean validParameters(List<String> values) {
		
		return values.stream().allMatch(v->Optional.ofNullable(v).isPresent());
	}
}
