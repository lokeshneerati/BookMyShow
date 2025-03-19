package com.acciojob.book_my_show.Services;

import com.acciojob.book_my_show.Enums.SeatType;
import com.acciojob.book_my_show.Models.Show;
import com.acciojob.book_my_show.Models.ShowSeat;
import com.acciojob.book_my_show.Models.Ticket;
import com.acciojob.book_my_show.Models.User;
import com.acciojob.book_my_show.Repositories.ShowRepository;
import com.acciojob.book_my_show.Repositories.ShowSeatRepository;
import com.acciojob.book_my_show.Repositories.TicketRepository;
import com.acciojob.book_my_show.Repositories.UserRepository;
import com.acciojob.book_my_show.Requests.BookTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;


    public Ticket bookTicket(BookTicketRequest bookTicketRequest){

        Show show = showRepository.findById(bookTicketRequest.getShowId()).get();
        User user = userRepository.findById(bookTicketRequest.getUserId()).get();

        Integer totalAmount = 0;
        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(ShowSeat showSeat : showSeatList) {

            String seatNo = showSeat.getSeatNo();
            if(bookTicketRequest.getRequestedSeats().contains(seatNo)) {
                showSeat.setIsBooked(Boolean.TRUE);

                if(showSeat.getSeatType().equals(SeatType.CLASSIC))
                    totalAmount = totalAmount+100;
                else
                    totalAmount = totalAmount+150;
            }

        }

        Ticket ticket = Ticket.builder().showDate(show.getShowDate())
                .showTime(show.getShowTime())
                .movieName(show.getMovie().getMovieName())
                .theaterName(show.getTheater().getName())
                .totalAmount(totalAmount)
                .show(show)
                .user(user)
                .build();

        showSeatRepository.saveAll(showSeatList);
        ticket = ticketRepository.save(ticket);

        return ticket;

    }

}
