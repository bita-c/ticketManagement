package com.zendesk.ticketing.domain.service;

import com.zendesk.ticketing.domain.model.Ticket;
import com.zendesk.ticketing.domain.model.TicketPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PagerTest {

  @Autowired
  Pager pager;

  @Test
  public void testIsPaginatedWhenCountIsMoreThanReturned() {
    Ticket[] tickets = new Ticket[1];

    TicketPage ticketPage = new TicketPage();
    ticketPage.setCount(3);
    ticketPage.setTickets(tickets);
    ticketPage.setNextPage("next page url");

    assertTrue(pager.isPaginated(ticketPage));
  }

  @Test
  public void testIsPaginatedWhenNextPageIsNull() {
    Ticket[] tickets = new Ticket[1];

    TicketPage ticketPage = new TicketPage();
    ticketPage.setCount(3);
    ticketPage.setTickets(tickets);

    assertFalse(pager.isPaginated(ticketPage));
  }

  @Test
  public void testHasNextPage() {
    TicketPage ticketPage = new TicketPage();
    ticketPage.setNextPage("next page url");

    assertTrue(pager.hasNextPage(ticketPage));

    ticketPage.setNextPage(null);

    assertFalse(pager.hasNextPage(ticketPage));
  }

  @Test
  public void testGetNextPageUrl() {
    TicketPage ticketPage = new TicketPage();
    ticketPage.setNextPage("next page url");

    assertEquals("next page url", pager.getNextPageUrl(ticketPage));
  }
}
