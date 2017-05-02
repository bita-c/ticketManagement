package com.zendesk.ticketing.infrastructure;

import com.zendesk.ticketing.domain.model.Ticket;
import com.zendesk.ticketing.domain.model.TicketPage;
import com.zendesk.ticketing.domain.model.TicketWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TicketRestClient.class)
public class TicketRestClientTest {

  @Autowired
  private TicketRestClient service;

  @MockBean
  private Config config;

  @MockBean
  private RestTemplate restTemplate;

  @Test
  public void testGetTicketWithIdReturnsTicketWhenResponseStatusOKAndHasTicket() {
    given(this.config.getBaseUrl()).willReturn("url");
    given(this.config.getTicketPath()).willReturn("/ticket");

    Ticket ticket = new Ticket();
    ticket.setId("id");
    TicketWrapper ticketWrapper = new TicketWrapper();
    ticketWrapper.setTicket(ticket);
    ResponseEntity<TicketWrapper> responseEntity = new ResponseEntity<>(ticketWrapper,HttpStatus.OK);

    given(this.restTemplate.exchange("url/ticket/id.json", HttpMethod.GET, null, TicketWrapper.class))
            .willReturn(responseEntity);

    Ticket returnedTicket = this.service.getTicket("id");

    assertNotNull(returnedTicket);
    assertEquals("id", returnedTicket.getId());
  }

  @Test(expected = TicketRestClientException.class)
  public void testGetTicketWithIdThrowsExceptionWhenResponseStatusNotOk() {

    given(this.config.getBaseUrl()).willReturn("url");
    given(this.config.getTicketPath()).willReturn("/ticket");

    Ticket ticket = new Ticket();
    ticket.setId("id");
    TicketWrapper ticketWrapper = new TicketWrapper();
    ticketWrapper.setTicket(ticket);
    ResponseEntity<TicketWrapper> responseEntity = new ResponseEntity<>(ticketWrapper,HttpStatus.INTERNAL_SERVER_ERROR);

    given(this.restTemplate.exchange("url/ticket/id.json", HttpMethod.GET, null, TicketWrapper.class))
            .willReturn(responseEntity);

    this.service.getTicket("id");
  }

  @Test
  public void testGetTicketsReturnsTicketPageWhenResponseStatusOKAndHasTickets() {

    given(this.config.getBaseUrl()).willReturn("url");
    given(this.config.getTicketsPath()).willReturn("/tickets");

    TicketPage ticketPage = new TicketPage();
    ticketPage.setCount(100);
    ResponseEntity<TicketPage> responseEntity = new ResponseEntity<>(ticketPage,HttpStatus.OK);

    given(this.restTemplate.exchange("url", HttpMethod.GET, null, TicketPage.class))
            .willReturn(responseEntity);

    TicketPage returnedTicketPage = this.service.getTickets(Optional.of("url"));

    assertNotNull(returnedTicketPage);
    assertEquals(100, returnedTicketPage.getCount());
  }

  @Test(expected = TicketRestClientException.class)
  public void testGetTicketsThrowsExceptionWhenResponseStatusNotOk() {

    given(this.config.getBaseUrl()).willReturn("url");
    given(this.config.getTicketsPath()).willReturn("/tickets");

    TicketPage ticketPage = new TicketPage();
    ticketPage.setCount(100);
    ResponseEntity<TicketPage> responseEntity = new ResponseEntity<>(ticketPage,HttpStatus.INTERNAL_SERVER_ERROR);

    given(this.restTemplate.exchange("url", HttpMethod.GET, null, TicketPage.class))
            .willReturn(responseEntity);

    this.service.getTickets(Optional.of("url"));

  }

}
