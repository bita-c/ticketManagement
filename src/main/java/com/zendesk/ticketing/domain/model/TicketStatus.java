package com.zendesk.ticketing.domain.model;

public enum TicketStatus {

  NEW("new"),
  OPEN("open"),
  PENDING("pending"),
  HOLD("hold"),
  SOLVED("solved"),
  CLOSED("closed");

  private String status;

  TicketStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return this.status;
  }

  public static TicketStatus getTicketStatus(String status) {
    for (TicketStatus ticketStatus : values()) {
      if (ticketStatus.getStatus().equals(status)) {
       return ticketStatus;
      }
    }
    return null;
  }

}
