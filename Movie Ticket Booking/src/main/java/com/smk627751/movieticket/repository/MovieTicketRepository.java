package com.smk627751.movieticket.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieTicketRepository {
	private static MovieTicketRepository movieRepo;
	Connection con;
	private MovieTicketRepository()
	{
		try {
			Class.forName("org.postgresql.Driver");
			String dbURL = "jdbc:postgresql://localhost:5432/smk627751";
			String user = "postgres";
			String pass = "627751";
			this.con = DriverManager.getConnection(dbURL, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String query)
	{
		try {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean insertQuery(String sql)
	{
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			int rowsAffected = ps.executeUpdate();
			return  rowsAffected > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static MovieTicketRepository getInstance()
	{
		if(movieRepo == null)
		{
			movieRepo = new MovieTicketRepository();
		}
		return movieRepo;
	}
}
