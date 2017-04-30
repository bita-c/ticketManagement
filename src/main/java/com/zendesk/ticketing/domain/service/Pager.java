package com.zendesk.ticketing.domain.service;

import com.zendesk.ticketing.domain.model.TicketListWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Pager {

  public boolean isPaginated(ResponseEntity<TicketListWrapper> responseEntity) {

    return responseEntity.getBody().getCount() > responseEntity.getBody().getTickets().size()
            && responseEntity.getBody().getNextPage() != null;
  }

  public boolean hasNextPage(ResponseEntity<TicketListWrapper> responseEntity) {

    return responseEntity.getBody().getNextPage()!= null;
  }

  public String getNextPageUrl(ResponseEntity<TicketListWrapper> responseEntity) {
    return responseEntity.getBody().getNextPage();
  }
}