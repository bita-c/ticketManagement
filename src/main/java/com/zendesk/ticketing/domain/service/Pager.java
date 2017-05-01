package com.zendesk.ticketing.domain.service;

import com.zendesk.ticketing.domain.model.TicketPage;
import org.springframework.stereotype.Component;

@Component
public class Pager {

  public boolean isPaginated(TicketPage ticketPage) {

    return ticketPage.getCount() > ticketPage.getTickets().size()
            && (ticketPage.getNextPage() != null && !ticketPage.getNextPage().isEmpty());
  }

  public boolean hasNextPage(TicketPage ticketPage) {

    return ticketPage.getNextPage() != null && !ticketPage.getNextPage().isEmpty();
  }

  public String getNextPageUrl(TicketPage ticketPage) {
    return ticketPage.getNextPage();
  }
}
