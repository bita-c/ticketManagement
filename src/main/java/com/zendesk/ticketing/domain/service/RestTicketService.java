package com.zendesk.ticketing.domain.service;

import com.zendesk.ticketing.Application;
import com.zendesk.ticketing.domain.model.TicketPage;
import com.zendesk.ticketing.infrastructure.Config;
import com.zendesk.ticketing.domain.model.Ticket;
import com.zendesk.ticketing.domain.model.TicketWrapper;
import com.zendesk.ticketing.infrastructure.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("TicketService")
public class RestTicketService implements TicketService {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  @Autowired
  private RestClient restClient;

  public Ticket getTicket(String id) {

    Ticket ticket = restClient.getTicket(id);
    LOGGER.info(ticket.toString());
    return ticket;

  }

  public TicketPage getTickets(Optional<String> optionalUrl) {

    TicketPage ticketPage = restClient.getTickets(optionalUrl);
    LOGGER.info(ticketPage.toString());
    return ticketPage;

  }

}
