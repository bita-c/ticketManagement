package com.zendesk.ticketing.domain.service;

import com.zendesk.ticketing.Application;
import com.zendesk.ticketing.domain.model.TicketPage;
import com.zendesk.ticketing.domain.model.Ticket;
import com.zendesk.ticketing.infrastructure.TicketRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("TicketService")
public class RestTicketService implements TicketService {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  @Autowired
  private TicketRestClient ticketRestClient;

  public Ticket getTicket(String id) {

    Ticket ticket = ticketRestClient.getTicket(id);
    LOGGER.info(ticket.toString());
    return ticket;

  }

  public TicketPage getTickets(Optional<String> optionalUrl) {

    TicketPage ticketPage = ticketRestClient.getTickets(optionalUrl);
    LOGGER.info(ticketPage.toString());
    return ticketPage;

  }

}
