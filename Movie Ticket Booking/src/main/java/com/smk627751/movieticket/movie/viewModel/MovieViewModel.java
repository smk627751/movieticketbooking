package com.smk627751.movieticket.movie.viewModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.smk627751.movieticket.movie.dto.Movie;
import com.smk627751.movieticket.movie.view.MovieView;
import com.smk627751.movieticket.repository.MovieTicketRepository;

public class MovieViewModel {
	private MovieTicketRepository movieRepo;
	private MovieView view;
	
	public MovieViewModel() {
		this.movieRepo = MovieTicketRepository.getInstance();
	}
	public MovieViewModel(MovieView view)
	{
		this.movieRepo = MovieTicketRepository.getInstance();
		this.view = view;
	}
	
	public List<Movie> getMovie() throws SQLException
	{
		List<Movie> list = new ArrayList<>();
		ResultSet rs = movieRepo.executeQuery("SELECT * FROM movie");
		while(rs.next())
		{
			list.add(new Movie(rs.getInt("id"),rs.getString("name"),rs.getString("morningShowTime"),rs.getString("afternoonShowTime"),rs.getString("eveningShowTime"),rs.getString("nightShowTime")));
		}
		Collections.sort(list,(a,b) -> (a.getName().length() - b.getName().length()));
		return list;
	}
	
	public void listMovies()
	{
		StringBuilder s = new StringBuilder();
		try {
			List<Movie> movies = getMovie();
			int index = 1;
			int maxLength = movies.get(movies.size() - 1).getName().length();
			char hLine[] = new char[maxLength + 53];
			Arrays.fill(hLine, '-');
			s.append("+"+new String(hLine)+"+\n");
			Arrays.fill(hLine, ' ');
			hLine[20] = 'M';
			hLine[24] = 'o';
			hLine[28] = 'v';
			hLine[32] = 'I';
			hLine[36] = 'E';
			s.append("|"+new String(hLine)+"|\n");
			Arrays.fill(hLine, '-');
			s.append("+"+new String(hLine)+"+\n");
			for(Movie movie : movies)
			{
				char arr[] = new char[maxLength - movie.getName().length() + 2];
				Arrays.fill(arr, ' ');
				s.append("|"+index++ +"\t| "+movie.getName()+new String(arr)+"| "+movie.getMorningShowTime()+" | "+movie.getAfternoonShowTime()+" | "+movie.getEveningShowTime()+" | "+movie.getNightShowTime()+" |\n");
			}
			s.append("+"+new String(hLine)+"+\n");
			view.onPrint(s.toString());
		} catch (SQLException e) {
			view.onError(e);
		}
	}
}
