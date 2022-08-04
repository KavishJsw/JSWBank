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
@WebServlet("/withServlet")
public class withServlet extends HttpServlet {
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
		String Amount = request.getParameter("Amount");

		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("delete from dashboard where amount ='"+Amount+"'");
			PrintWriter out = response.getWriter();
			if (result>0) {
			out.println("\r\n"
					+ "<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "      \r\n"
					+ "<head>\r\n"
					+ "    <title>Withdrawal Page</title>\r\n"
					+ "        "
					+ "        \r\n"
					+ "    </title>\r\n"
					+ "      \r\n"
					+ "    "
					+ "    <style>\r\n"
					+ "        .CSS {\r\n"
					+ "            background-color: white;\r\n"
					+ "            border: 2px solid black;\r\n"
					+ "            color: green;\r\n"
					+ "            padding: 5px 10px;\r\n"
					+ "            text-align: center;\r\n"
					+ "            display: inline-block;\r\n"
					+ "            font-size: 20px;\r\n"
					+ "            margin: 10px 30px;\r\n"
					+ "            cursor: pointer;\r\n"
					+ "        }\r\n"
					+ "    </style>\r\n"
					+ "</head>\r\n"
					+ "  \r\n"
					+ "<body>\r\n"
					+ "    <h1>Widthrawl Succesfully Done!</h1>\r\n"
					+ "      \r\n"
					+ "    <!-- Adding button inside the link tag -->\r\n"
					+ "    <a href=dashboard.html>\r\n"
					+ "        <button class=\"CSS\">\r\n"
					+ "            Back to Dashboard\r\n"
					+ "        </button>\r\n"
					+ "    </a>\r\n"
					+ "</body>\r\n"
					+ "  \r\n"
					+ "</html> ");
			}
			else {
				out.println("<h1>Error in Withdrawing try again</h1>");
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
