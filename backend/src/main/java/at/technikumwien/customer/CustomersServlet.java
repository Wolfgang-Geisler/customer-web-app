package at.technikumwien.customer;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// http://localhost:8081/customers

@WebServlet("customersservlet")
@SuppressWarnings("serial")
public class CustomersServlet extends HttpServlet {
	@Inject
	private CustomersService customersService;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var out = response.getWriter();
		
		var html = new StringBuilder(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"utf-8\">\r\n" + 
				"<title>Customers</title>\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body>\r\n" + 
				"<h1>Customers</h1>\r\n"
				);
				
				var customersList = customersService.findAll();
				for (var customers : customersList) {
					html.append(
						"<p>" + customers.getFirstName() + "</p>" +
						"<p>" + customers.getSecondName() + "</p>" +
						"<p>" + customers.getDateOfBirth() + "</p>" +
						"<p>" + customers.isActivated() + "</p>"
					);
					
				}

				html.append(
					"</body>" +
					"</html"
				);
		
		out.println(html.toString());
	}
}
