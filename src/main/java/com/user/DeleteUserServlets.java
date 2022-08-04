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

@WebServlet("/deleteServlet")
public class DeleteUserServlets extends HttpServlet {
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

	
	String email = request.getParameter("email");
	
	try {
		Statement statement = connection.createStatement();
		int result = statement.executeUpdate(" delete from user where email='"+email+"'");
		PrintWriter out = response.getWriter();
		if(result>0) {
		out.print("<html>"
				+ "<h1>User Deleted Successfully !</h1>"
				+ "</html>");
		}
		else {
			out.print("<h1>User Not Found in The Database</h1>");
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
