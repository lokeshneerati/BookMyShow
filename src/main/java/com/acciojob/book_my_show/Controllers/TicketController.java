package com.acciojob.book_my_show.Controllers;

import com.acciojob.book_my_show.Models.Ticket;
import com.acciojob.book_my_show.Requests.BookTicketRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @PostMapping("bookTicket")
    public Ticket bookTicket(@RequestBody BookTicketRequest bookTicketRequest) {

    }


}
