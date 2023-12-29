package com.smk627751.movieticket.booking.viewModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.smk627751.movieticket.booking.dto.Viewer;
import com.smk627751.movieticket.booking.view.BookingView;
import com.smk627751.movieticket.movie.dto.Movie;
import com.smk627751.movieticket.movie.viewModel.MovieViewModel;
import com.smk627751.movieticket.repository.MovieTicketRepository;

public class BookingViewModel {
	private BookingView bookingView;
	private MovieViewModel movieViewModel;
	private MovieTicketRepository movieRepo;
	private int[] classes = new int[3];
	private List<Movie> movies;
	private List<Viewer> viewers = new ArrayList<>();
	private String booked;
	private String[] dates = new String[7];
	public BookingViewModel(BookingView bookingView) {
		this.generateDates();
		this.bookingView = bookingView;
		this.movieRepo = MovieTicketRepository.getInstance();
		this.movieViewModel = new MovieViewModel();
		try {
			this.movies = movieViewModel.getMovie();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void generateDates()
	{
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		for(int i = 0; i < dates.length; i++)
		{
			dates[i] = f.format(now);
			now = now.plusDays(1);
		}
	}
	public void showDates()
	{
		StringBuilder s = new StringBuilder();
		int index = 1;
		s.append("+----------------+\n");
		for(String date : dates)
		{
			s.append("| " +index++ +". "+" "+ date+" |\n");
		}
		s.append("+----------------+\n");
		bookingView.onPrint(s.toString());
	}
	public String[] getDates() {
		return dates;
	}
	public void setDates(String[] dates) {
		this.dates = dates;
	}
	public void bookTicket(int choice,String showTime,String name, String phone, String seatNo, int count, int date)
	{
		int first = 0, second = 0, third = 0;
		if(seatNo.length() == 2)
		{
			if(booked != null && booked.contains(seatNo))
			{
				bookingView.onPrint(seatNo + " is already booked..");
				return;
			}
			if(seatNo.startsWith("A") || seatNo.startsWith("B"))
			{
				first++;
			}
			if(seatNo.startsWith("C") || seatNo.startsWith("D"))
			{
				second++;
			}
			if(seatNo.startsWith("E") || seatNo.startsWith("F") || seatNo.startsWith("G"))
			{
				third++;
			}
		}
		String[] seats = seatNo.split(",");
		for(String seat : seats)
		{
			if(booked != null && booked.contains(seat))
			{
				bookingView.onPrint(seat + " is already booked..");
				return;
			}
			if(seat.startsWith("A") || seat.startsWith("B"))
			{
				first++;
			}
			if(seat.startsWith("C") || seat.startsWith("D"))
			{
				second++;
			}
			if(seat.startsWith("E") || seat.startsWith("F") || seat.startsWith("G"))
			{
				third++;
			}
		}
		if(first > classes[0] || second > classes[0] || third > classes[0])
		{
			bookingView.onPrint("Not enough seats available");
			return;
		}
		String movie = movies.get(choice - 1).getName();
		String sql = "INSERT INTO viewer (movie,name,phone,count,date,seatno,showtime) VALUES('"+movie+"','"+name+"','"+phone+"',"+count+",'"+dates[date - 1]+"','"+seatNo+"','"+showTime+"')";
		boolean inserted = movieRepo.insertQuery(sql);
		if(inserted)
		{
			String avail = "UPDATE booking SET first = "+(classes[0] - first)+",second = "+(classes[1] - second)+",third = "+(classes[2] - third)+" WHERE name = '"+movie+"' and showtime = '"+showTime+"'";
			movieRepo.insertQuery(avail);
			booked = booked != null ? (booked+","+seatNo) : seatNo;
			avail = "UPDATE booking SET booked = '"+booked+"' WHERE name = '"+movie+"' and showtime = '"+showTime+"'";
			movieRepo.insertQuery(avail);
			bookingView.onPrint("Booked successfully");
			printTicket(name);
		}
		else
		{
			bookingView.onPrint("Error occured");
		}
	}
	
	public void printTicket(String name)
	{
		StringBuilder s = new StringBuilder();
		String sql = "SELECT * FROM viewer WHERE name = '"+name+"'";
		ResultSet rs = movieRepo.executeQuery(sql);
		Viewer viewer = null;
		try {
			while(rs.next())
			{
				viewer = new Viewer(rs.getString("movie"),rs.getString("showtime"),rs.getString("name"),rs.getString("phone"),rs.getInt("count"),rs.getString("seatno"),rs.getString("date"));
				viewers.add(viewer);
				s.append(viewer.toString());
			}
			bookingView.onPrint(s.toString());
			s.setLength(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void showAvailableTickets(int choice, String showTime) 
	{
		StringBuilder s = new StringBuilder();
		String movie = movies.get(choice - 1).getName();
		String sql = "SELECT * FROM booking WHERE name = '"+movie+"' and showtime = '"+showTime+"'";
		ResultSet rs = movieRepo.executeQuery(sql);
		try {
			while(rs.next())
			{
				classes[0] = rs.getInt("first");
				classes[1] = rs.getInt("second");
				classes[2] = rs.getInt("third");
				booked = rs.getString("booked");
				s.append("Movie: "+movie+"\tShow time: "+showTime+"\n"+"first: "+classes[0]+"\t"+"second: "+classes[1]+"\t"+"third: "+classes[2]+"\n");
			}
			bookingView.onPrint(s.toString());
			s.setLength(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int[][] seats = new int[7][10];
		if(booked != null)
		{
			String[] bookedSeats = booked.split(",");
			for(String bookedSeat : bookedSeats)
			{
				seats[bookedSeat.charAt(0) - 65][Integer.parseInt(bookedSeat.substring(1)) - 1] = 1;
			}
		}
		char c = 'A';
		s.append("   ");
		for(int i = 0; i < seats[0].length; i++)
		{
			if(i == 5)
			{
				s.append(" ");
			}
			s.append((i + 1)+"  ");
		}
		s.append("\n");
		for(int i = 0; i < seats.length; i++)
		{
			if(i == 2 || i == 4)
			{
				s.append("\n");
			}
			s.append(c++ +" ");
			for(int j = 0; j < seats[0].length; j++)
			{
				if(j == 5)
				{
					s.append(" ");
				}
				if(seats[i][j] == 1)
				{
					s.append("\033[1m\033[35m[_]\033[0m");
				}
				else
				{
					s.append("[_]");
				}
			}
			s.append("\n");
		}
		bookingView.onPrint(s.toString());
	}
}
