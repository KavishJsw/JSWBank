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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class CreateUserServlets
 */

@WebServlet("/readServlet")
public class ReadUserServlet extends HttpServlet {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	try {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from user");
		PrintWriter out=response.getWriter();
		
		out.print("<table>");
		out.print("<tr>");
		out.print("<th>");
		out.print("First Name");
		out.print("</th>");
		out.print("<th>");
		out.print("Last Name");
		out.print("</th>");
		out.print("<th>");
		out.print("Email");
		out.print("</th>");
		out.print("</tr>");
		while(resultSet.next()) {
			out.println("<tr>");
			out.println("<td>");
			out.println(resultSet.getString(1));
			out.println("</td>");
			out.println("<td>");
			out.println(resultSet.getString(2));
			out.println("</td>");
			out.println("<td>");
			out.println(resultSet.getString(3));
			out.println("</td>");
			out.println("</tr>");

			
		}
		out.print("</table>");

	
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
