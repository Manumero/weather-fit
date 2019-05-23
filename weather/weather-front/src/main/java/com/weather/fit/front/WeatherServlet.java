package com.weather.fit.front;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.weather.fit.front.client.WeatherClient;
import com.weather.fit.front.model.ResultDTO;
import com.weather.fit.front.model.Weather;

@WebServlet(value = "/weatherServlet", name = "helloWorldServlet")
public class WeatherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(WeatherServlet.class);

	public WeatherServlet() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("service at: ").append(request.getContextPath()).append("<br>");
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String city = request.getParameter("country");
		ResultDTO<Weather> wheatherInfoObject;
		try {
			wheatherInfoObject = WeatherClient.getWeatherInfo(city);
			StringBuilder outputContent = new StringBuilder();
			if (Optional.ofNullable(wheatherInfoObject).isPresent() && wheatherInfoObject.getStatus().equals("ok")) {

				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat sdfsun = new SimpleDateFormat("hh:mm a");

				outputContent.append("<!DOCTYPE html>");
				outputContent.append("<html>");
				outputContent.append("<head>");
				outputContent.append("<meta charset=\"ISO-8859-1\"/>");
				outputContent.append("<title>Weather Test</title>");
				outputContent.append("</head>");
				outputContent.append("<body>");
				outputContent.append("<table>");
				outputContent.append("<tr>");
				outputContent.append("<td>City: " + wheatherInfoObject.getDetails().getName() + "</td>");
				outputContent.append("<tr>");
				outputContent.append("<td>Date: " + sdf.format(new Date(wheatherInfoObject.getDetails().getTimestamp() * 1000)) + "</td>");
				outputContent.append("</tr>");
				outputContent.append("<tr>");
				outputContent.append("<td>Weather: " + wheatherInfoObject.getDetails().getWeatherDescription() + "</td>");
				outputContent.append("</tr>");
				outputContent.append("<tr>");
				outputContent.append("<td>Temperature Celsius: " + wheatherInfoObject.getDetails().getCelcius() + "</td>");
				outputContent.append("</tr>");
				outputContent.append("<tr>");
				outputContent.append("<td>Temperature Fahrenheit: " + wheatherInfoObject.getDetails().getFahrenheit() + "</td>");
				outputContent.append("</tr>");
				outputContent.append("<tr>");
				outputContent.append("<td>Sunrise: " + sdfsun.format(new Date(wheatherInfoObject.getDetails().getSunrise() * 1000)) + "</td>");
				outputContent.append("</tr>");
				outputContent.append("<tr>");
				outputContent.append("<td>Sunset: " + sdfsun.format(new Date(wheatherInfoObject.getDetails().getSunset() * 1000)) + "</td>");
				outputContent.append("</tr>");
				outputContent.append("</table>");
				outputContent.append("</body>");
				outputContent.append("</html>");
			} else {
				outputContent.append(wheatherInfoObject.getMessage());
			}

			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(outputContent.toString());

			doPost(request, response);
		} catch (Exception e) {
			logger.error("Exception", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
	}

}
