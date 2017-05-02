package com.zendesk.ticketing.infrastructure;

import com.zendesk.ticketing.domain.model.Ticket;
import com.zendesk.ticketing.domain.model.TicketPage;
import com.zendesk.ticketing.domain.model.TicketWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class TicketRestClient {

  @Autowired
  private Config config;

  @Autowired
  private RestTemplate restTemplate;


  public Ticket getTicket(String id) {

    String url = config.getBaseUrl().concat(config.getTicketPath()).concat("/").concat(id).concat(".json");

    ResponseEntity<TicketWrapper> ticketResponseEntity = restTemplate.exchange(
            url, HttpMethod.GET, null, TicketWrapper.class);


    if (ticketResponseEntity.getStatusCode() != null && ticketResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
      return ticketResponseEntity.getBody().getTicket();
    } else {
      throw new TicketRestClientException("Unable to process request to remote server!");
    }
  }

  public TicketPage getTickets(Optional<String> optionalUrl) {

    String url = optionalUrl.orElse(config.getBaseUrl().concat(config.getTicketsPath()));

    ResponseEntity<TicketPage> ticketResponseEntity = restTemplate.exchange(
            url, HttpMethod.GET, null, TicketPage.class);

    if (ticketResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
      TicketPage ticketPage = ticketResponseEntity.getBody();
      return ticketPage;
    } else {
      throw new TicketRestClientException("Unable to process request to remote server!");
    }
  }

}
