package com.zendesk.ticketing.domain.service;

import com.zendesk.ticketing.domain.model.Ticket;
import com.zendesk.ticketing.domain.model.TicketPage;

import java.util.Optional;

public interface TicketService {

  Ticket getTicket(String id);

  TicketPage getTickets(Optional<String> url);
}
