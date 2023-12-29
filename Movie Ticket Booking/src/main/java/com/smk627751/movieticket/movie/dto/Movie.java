package com.smk627751.movieticket.movie.dto;

public class Movie {
	private int id;
	private String name;
	private String morningShowTime;
	private String afternoonShowTime;
	private String eveningShowTime;
	private String nightShowTime;
	
	public Movie(int id,String name,String morningShowTime, String afternoonShowTime, String eveningShowTime, String nightShowTime)
	{
		this.id = id;
		this.name = name;
		this.setMorningShowTime(morningShowTime);
		this.setAfternoonShowTime(afternoonShowTime);
		this.setEveningShowTime(eveningShowTime);
		this.setNightShowTime(nightShowTime);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMorningShowTime() {
		return morningShowTime;
	}

	public void setMorningShowTime(String morningShowTime) {
		this.morningShowTime = morningShowTime;
	}

	public String getAfternoonShowTime() {
		return afternoonShowTime;
	}

	public void setAfternoonShowTime(String afternoonShowTime) {
		this.afternoonShowTime = afternoonShowTime;
	}

	public String getEveningShowTime() {
		return eveningShowTime;
	}

	public void setEveningShowTime(String eveningShowTime) {
		this.eveningShowTime = eveningShowTime;
	}

	public String getNightShowTime() {
		return nightShowTime;
	}

	public void setNightShowTime(String nightShowTime) {
		this.nightShowTime = nightShowTime;
	}

	
}
