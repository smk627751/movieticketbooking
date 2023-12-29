package com.smk627751.movieticket.booking.view;

import java.util.Scanner;

import com.smk627751.movieticket.booking.viewModel.BookingViewModel;

public class BookingView {
	private BookingViewModel bookingViewModel;
	private Scanner sc = new Scanner(System.in);
	private final String BOLD = "\033[1m";
	private final String RESET = "\033[0m";
	private final String BG = BOLD + "\033[45m";
	private String[] shows = {"10:00 AM","2:00 PM","6:00 PM","10:00 PM"};
	public BookingView()
	{
		this.bookingViewModel = new BookingViewModel(this);
	}
	
	public void bookTicket()
	{
		bookingViewModel.showDates();
		onPrint(BG+"Enter the which date 1/2/3/4/5/6/7:"+RESET);
		int date = sc.nextInt();
		if(date > 7)
		{
			onPrint("Invalid input enter 1/2/3/4/5/6/7");
			return;
		}
		onPrint("\n");
		onPrint(BG+"choose the movie:"+RESET);
		int choice = sc.nextInt();
		onPrint("\n");
		onPrint(BG+"Enter the show time 1/2/3/4: "+RESET);
		int index = sc.nextInt() - 1;
		if(index > 3)
		{
			onPrint("Invalid input enter 1/2/3/4");
			return;
		}
		String showTime = shows[index];
		onPrint("\n");
		bookingViewModel.showAvailableTickets(choice,showTime);
		onPrint(BG + "Enter the no of tickets: " + RESET);
		int count = sc.nextInt();
		sc.nextLine();
		onPrint(BG + "Enter the seat no as comma(,) seperated value: " + RESET);
		String seatNo = sc.next();
		onPrint(BG + "Enter your name: " + RESET);
		String name = sc.nextLine();
		name += sc.nextLine();
		onPrint(BG + "Enter your phone number:" + RESET);
		String phone = sc.next();
		bookingViewModel.bookTicket(choice, showTime, name, phone, seatNo, count, date);
	}
	
	public void showAvaliableTickets()
	{
		onPrint(BG + "choose the movie:" + RESET);
		int choice = sc.nextInt();
		onPrint("\n");
		onPrint(BG + "Enter the show time 1/2/3/4: " + RESET);
		int index = sc.nextInt() - 1;
		if(index > 3)
		{
			onPrint("Invalid input enter 1/2/3/4");
			return;
		}
		String showTime = shows[index];
		onPrint("\n");
		bookingViewModel.showAvailableTickets(choice,showTime);
	}

	public void onPrint(String s) {
		System.out.println(s);
	}
}
