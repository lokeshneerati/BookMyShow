package com.acciojob.book_my_show.Services;

import com.acciojob.book_my_show.Models.Movie;
import com.acciojob.book_my_show.Repositories.MovieRepository;
import com.acciojob.book_my_show.Requests.AddMovieRequest;
import com.acciojob.book_my_show.Requests.UpdateMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(AddMovieRequest movieRequest) {

        //From my request Entry i am creating the entity : bcz entity saves into the DB
        Movie movie = new  Movie();
        movie.setMovieName(movieRequest.getMovieName());
        movie.setDuration(movieRequest.getDuration());
        movie.setLanguage(movieRequest.getLanguage());
        movie.setRating(movieRequest.getRating());
        movie.setReleaseDate(movieRequest.getReleaseDate());

        movie = movieRepository.save(movie);
        return "Movie has been added to the DB with movieId "+movie.getMovieId();
    }

    public String updateMovieAttributes(UpdateMovieRequest movieRequest) {

        Movie movie = movieRepository.findMovieByMovieName(movieRequest.getMovieName());

        movie.setLanguage(movieRequest.getNewLanguage());
        movie.setRating(movieRequest.getNewRating());

        movieRepository.save(movie);
        return "Movie Attributes have been updated";

    }
}
