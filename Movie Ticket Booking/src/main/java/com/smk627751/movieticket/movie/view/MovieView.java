package com.smk627751.movieticket.movie.view;


import com.smk627751.movieticket.movie.viewModel.MovieViewModel;

public class MovieView {
	private MovieViewModel viewModel;
	public MovieView()
	{
		this.viewModel = new MovieViewModel(this);
	}
	public void onPrint(String s)
	{
		System.out.println(s);
	}
	public void onError(Exception e)
	{
		System.out.println(e.getMessage());
	}
	public void init()
	{
		viewModel.listMovies();
	}
}
