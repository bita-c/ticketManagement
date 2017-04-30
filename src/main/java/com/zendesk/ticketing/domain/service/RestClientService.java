package com.zendesk.ticketing.domain.service;

import com.zendesk.ticketing.Application;
import com.zendesk.ticketing.Config;
import com.zendesk.ticketing.Pager;
import com.zendesk.ticketing.domain.model.Ticket;
import com.zendesk.ticketing.domain.model.TicketListWrapper;
import com.zendesk.ticketing.domain.model.TicketWrapper;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component("restClient")
@Service
public class RestClientService implements TicketService{

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private Pager pager;

  public Ticket getTicket(String id) {

    String getTicketUrl = Config.BASE_URL.concat(Config.TICKET_PATH);
    Map<String, String> valuesMap = new HashMap<>();
    valuesMap.put("id", id);
    StrSubstitutor sub = new StrSubstitutor(valuesMap, "{", "}");
    String url = sub.replace(getTicketUrl);

    ResponseEntity<TicketWrapper> ticketResponseEntity = restTemplate.exchange(
            url, HttpMethod.GET, null, TicketWrapper.class);

    LOGGER.info(ticketResponseEntity.getBody().getTicket().toString());

    return ticketResponseEntity.getBody().getTicket();
  }

//  public List<Ticket> getTickets() {
//
//    String url = Config.BASE_URL.concat(Config.TICKETS_PATH);
//
//    ResponseEntity<TicketListWrapper> ticketResponseEntity = restTemplate.exchange(
//            url, HttpMethod.GET, null, TicketListWrapper.class);
//
//    List<Ticket> ticketList = Arrays.asList(ticketResponseEntity.getBody().getTickets());
//
//    ticketList.forEach(ticket -> LOGGER.info(ticket.toString()));
//
//    return ticketList;
//
//  }

  public List<Ticket> getTicketsWithPaging() {

    String url = Config.BASE_URL.concat(Config.TICKETS_PATH);

    ResponseEntity<TicketListWrapper> ticketResponseEntity = restTemplate.exchange(
            url, HttpMethod.GET, null, TicketListWrapper.class);

    List<Ticket> tickets = new ArrayList<>();
    tickets.addAll(ticketResponseEntity.getBody().getTickets());

    if (pager.isPaginated(ticketResponseEntity)) {
      tickets.addAll(getPagedTickets(ticketResponseEntity));
    }

    tickets.forEach(ticket -> LOGGER.info(ticket.toString()));

    return tickets;
  }

  private List<Ticket> getPagedTickets(ResponseEntity<TicketListWrapper> ticketResponseEntity) {

    List<Ticket> pagedTickets = new ArrayList<>();
    while(pager.hasNextPage(ticketResponseEntity)) {

      String nextPageUrl = pager.getNextPageUrl(ticketResponseEntity);
      ticketResponseEntity = restTemplate.exchange(
              nextPageUrl, HttpMethod.GET, null, TicketListWrapper.class);
      pagedTickets.addAll(ticketResponseEntity.getBody().getTickets());
    }
    return pagedTickets;
  }

}
