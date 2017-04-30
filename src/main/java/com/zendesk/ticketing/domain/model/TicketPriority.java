package com.zendesk.ticketing.domain.model;

public enum TicketPriority {

  URGENT("urgent"),
  HIGH("high"),
  NORMAL("normal"),
  LOW("low");

  private String priority;

  TicketPriority(String priority) {
    this.priority = priority;
  }

  public String getPriority() {
    return this.priority;
  }

  public static TicketPriority getTicketPriority(String priority) {
    for (TicketPriority ticketPriority : values()) {
      if (ticketPriority.getPriority().equals(priority)) {
        return ticketPriority;
      }
    }
    return null;
  }
}
