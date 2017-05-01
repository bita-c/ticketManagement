package com.zendesk.ticketing.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketPage {

  private List<Ticket> tickets;
  private String nextPage;
  private String previousPage;
  private int count;

  public TicketPage() {

  }

  public List<Ticket> getTickets() {
    return tickets;
  }

  public void setTickets(Ticket[]  tickets) {
    this.tickets = Arrays.asList(tickets);
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  @JsonProperty("previous_page")
  public String getPreviousPage() {
    return previousPage;
  }

  public void setPreviousPage(String previousPage) {
    this.previousPage = previousPage;
  }

  @JsonProperty("next_page")
  public String getNextPage() {
    return nextPage;
  }

  public void setNextPage(String nextPage) {
    this.nextPage = nextPage;
  }

  @Override
  public String toString() {
    return "TicketPage{" +
            "nextPage='" + nextPage + '\'' +
            ", previousPage=" + previousPage + '\'' +
            ", count=" + count +
            '}';
  }
}
