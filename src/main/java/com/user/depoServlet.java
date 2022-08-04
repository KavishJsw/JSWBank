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
 * Servlet implementation class depoServlet
 */
@WebServlet("/depoServlet")
public class depoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private Connection connection;

	public void init(ServletConfig config) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			   connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Jsw@2022");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Accountno = request.getParameter("Accountno");
		String Amount = request.getParameter("Amount");

		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("insert into dashboard values('"+Accountno+"','"+Amount+"')");
			PrintWriter out = response.getWriter();
			if (result>0) {
			out.println("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "	\r\n"
					+ "<head>\r\n"
					+ "	<title>\r\n"
					+ "		"
					+ "		Deposit Page"
					+ "	</title>\r\n"
					+ "	\r\n"
					+ "	<!-- Style to create button -->\r\n"
					+ "	<style>\r\n"
					+ "		.CSS {\r\n"
					+ "			background-color: white;\r\n"
					+ "			border: 2px solid black;\r\n"
					+ "			color: green;\r\n"
					+ "			padding: 5px 10px;\r\n"
					+ "			text-align: center;\r\n"
					+ "			display: inline-block;\r\n"
					+ "			font-size: 20px;\r\n"
					+ "			margin: 10px 30px;\r\n"
					+ "			cursor: pointer;\r\n"
					+ "			text-decoration:none;\r\n"
					+ "		}\r\n"
					+ "	</style>\r\n"
					+ "</head>\r\n"
					+ "\r\n"
					+ "<body>\r\n"
					+ "	<h1>Deposit Succesfull!</h1>\r\n"
					+ "	\r\n"
					+ "	"
					+ "	<a href=dashboard.html class=\"CSS\">\r\n"
					+ "		Back to Dashboard\r\n"
					+ "	</a>\r\n"
					+ "</body>\r\n"
					+ "\r\n"
					+ "</html>\r\n"
					+ ""
					);
			}
			else {
				out.println("<h1>Error in depositing try again</h1>");
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
