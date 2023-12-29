package com.smk627751.movieticket.booking.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Viewer {
	private String movie;
	private String showtime;
	private String name;
	private long phone;
	private int count;
	private String allocated;
	private Date date;
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	
	public Viewer(String movie,String showTime,String name,String phone,int count,String allocated,String date)
	{
		this.movie = movie;
		this.showtime = showTime;
		this.name = name;
		this.phone = Long.parseLong(phone);
		this.count = count;
		this.allocated = allocated;
		try {
			this.date = sd.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getShowtime() {
		return showtime;
	}
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getAllocated() {
		return allocated;
	}
	public void setAllocated(String allocated) {
		this.allocated = allocated;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append("\n");
		s.append(" Movie: "+movie+"\tShow time: "+showtime+"\tdate: "+sd.format(date)+"\n");
		s.append(" Name: "+name+"\tSeat no: "+allocated+"\n");
		return s.toString();
	}
}
