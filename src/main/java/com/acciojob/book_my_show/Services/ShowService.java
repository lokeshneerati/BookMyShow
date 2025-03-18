package com.acciojob.book_my_show.Services;

import com.acciojob.book_my_show.Models.*;
import com.acciojob.book_my_show.Repositories.MovieRepository;
import com.acciojob.book_my_show.Repositories.ShowRepository;
import com.acciojob.book_my_show.Repositories.ShowSeatRepository;
import com.acciojob.book_my_show.Repositories.TheaterRepository;
import com.acciojob.book_my_show.Requests.AddShowRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;


    public String addShow(AddShowRequest showRequest){

        Movie movie = movieRepository.findMovieByMovieName(showRequest.getMovieName());
        Theater theater = theaterRepository.findById(showRequest.getTheaterId()).get();


        Show show = Show.builder().showDate(showRequest.getShowDate())
                .showTime(showRequest.getShowTime())
                .movie(movie)
                .theater(theater)
                .build();

        show = showRepository.save(show);

        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        List<ShowSeat> showSeatList = new ArrayList<>();

        for(TheaterSeat theaterSeat:theaterSeatList){

            ShowSeat showSeat = ShowSeat.builder().seatNo(theaterSeat.getSeatNo())
                    .seatType(theaterSeat.getSeatType())
                    .isBooked(Boolean.FALSE)
                    .isFoodAttached(Boolean.FALSE)
                    .show(show).build();

            showSeatList.add(showSeat);
        }

        showSeatRepository.saveAll(showSeatList);

        return "The show has been saved to the DB with showId "+show.getShowId();

    }

}
