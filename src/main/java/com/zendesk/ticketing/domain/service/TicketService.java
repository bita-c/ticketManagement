package com.zendesk.ticketing.domain.service;

import com.zendesk.ticketing.domain.model.Ticket;

import java.util.List;

public interface TicketService {

  Ticket getTicket(String id);

  List<Ticket> getTicketsWithPaging();
}
