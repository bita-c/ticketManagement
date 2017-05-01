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

  @Autowired
  private Config config;

  public Ticket getTicket(String id) {

    String url = config.getBaseUrl().concat(config.getTicketPath()).concat("/").concat(id).concat(".json");
    ResponseEntity<TicketWrapper> ticketResponseEntity = restClient.getRestTemplate().exchange(
            url, HttpMethod.GET, null, TicketWrapper.class);

    if (ticketResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
      LOGGER.info(ticketResponseEntity.getBody().getTicket().toString());
      return ticketResponseEntity.getBody().getTicket();
    } else {
      throw new RestClientException("Unable to process request at this time!");
    }

  }

  public TicketPage getTickets(Optional<String> optionalUrl) {

    String url = optionalUrl.orElse(config.getBaseUrl().concat(config.getTicketsPath()));

    ResponseEntity<TicketPage> ticketResponseEntity = restClient.getRestTemplate().exchange(
            url, HttpMethod.GET, null, TicketPage.class);

    if (ticketResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
      TicketPage ticketPage = ticketResponseEntity.getBody();
      LOGGER.info(ticketPage.toString());
      return ticketPage;
    } else {
      throw new RestClientException("Unable to process request at this time!");
    }
  }

}
