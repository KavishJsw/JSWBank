package com.user;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class CreateUserServlets
 */

@WebServlet("/addServlet")
public class CreateUserServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	public void init(ServletConfig config) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Jsw@2022");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("insert into user values('" + firstName + "','" + lastName + "','"
					+ email + "','" + password + "')");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.print("<h1>USER CREATED</h1>" + "<html>\r\n" + "	\r\n" + "<head>\r\n" + "	<title>\r\n"
						+ "		Create an HTML button that\r\n" + "		acts like a link\r\n" + "	</title>\r\n"
						+ "	\r\n" + "	<!-- Style to create button -->\r\n" + "	<style>\r\n" + "		.GFG {\r\n"
						+ "			background-color: white;\r\n" + "			border: 2px solid black;\r\n"
						+ "			color: green;\r\n" + "			padding: 5px 10px;\r\n"
						+ "			text-align: center;\r\n" + "			display: inline-block;\r\n"
						+ "			font-size: 20px;\r\n" + "			margin: 10px 30px;\r\n"
						+ "			cursor: pointer;\r\n" + "		}\r\n" + "	</style>\r\n" + "</head>\r\n" + "\r\n"
						+ "<body>\r\n" + "	<h1>Congrats ! Your Accout Has been Created Click on button To Continue</h1>\r\n" + "	\r\n"
						+ "	<!-- Adding link to the button on the onclick event -->\r\n"
						+ "	<button class=\"GFG\"\r\n"
						+ "	onclick=\"window.location.href = 'dashboard.html';\">\r\n"
						+ "		Click Here\r\n" + "	</button>\r\n" + "</body>\r\n" + "\r\n"
						+ "</html>					\r\n" + "");
			} else {
				out.print("<h1>Error Creating the user</h1><br>"

				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
