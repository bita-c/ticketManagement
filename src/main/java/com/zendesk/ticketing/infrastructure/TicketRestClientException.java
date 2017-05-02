package com.zendesk.ticketing.infrastructure;

public class TicketRestClientException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String details;

  public TicketRestClientException(String passedReason, String passedDetails) {
    super(passedReason);
    this.details = passedDetails;
  }

  public TicketRestClientException(String passedReason) {
    super(passedReason);
  }

  public void setFaultInfo(String passedDetails) {
    this.details = passedDetails;

  }

  public String getFaultInfo() {
    return this.details;
  }
}
