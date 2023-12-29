package com.smk627751.movieticket;

import java.util.Scanner;

import com.smk627751.movieticket.booking.view.BookingView;
import com.smk627751.movieticket.movie.view.MovieView;

public class Main {
	private static MovieView view = new MovieView();
	private static BookingView bookingView = new BookingView();
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.println("+----------------------+");
			System.out.println("| 1. Show movies       |\n| 2. Available Tickets |\n| 3. Book Ticket       |");
			System.out.println("+----------------------+");
			System.out.println("Enter the choice: ");
			switch(sc.nextInt())
			{
				case 1 :{
					view.init();
					break;
				}
				case 2 :
				{
					view.init();
					bookingView.showAvaliableTickets();
					break;
				}
				case 3 :
				{
					bookingView.bookTicket();
					break;
				}
				case 0:
				{
					sc.close();
					System.exit(0);
				}
				default:System.out.println("Invalid choice");break;
			}
		}while(true);
	}
}
