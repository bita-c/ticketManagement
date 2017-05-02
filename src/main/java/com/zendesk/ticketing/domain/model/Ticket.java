package com.zendesk.ticketing.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

  private static final Logger LOGGER = LoggerFactory.getLogger(Ticket.class);

  private String url;
  private String id;
  private String createdAt;
  private String updatedAt;
  private String subject;
  private String description;
  private TicketPriority priority;
  private TicketStatus status;

  public Ticket() {

  }

  public String getStatus() {
    return status.getStatus();
  }

  public void setStatus(String status) {
    this.status = TicketStatus.getTicketStatus(status);
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @JsonProperty("created_at")
  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  @JsonProperty("updated_at")
  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPriority() {
    return priority.getPriority();
  }

  public void setPriority(String priority) {
    this.priority = TicketPriority.getTicketPriority(priority);
  }

  private String getFormattedDate(String date) {
    final DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    final DateFormat finalDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String resultedDate = date;
    try {
      Date formattedDate = inputDateFormat.parse(date);
      resultedDate = finalDateFormat.format(formattedDate);

    } catch(ParseException e) {
      LOGGER.warn("Unable to process date: " + date);
    }
    return resultedDate;
  }

  @Override
  public String toString() {
    return "Ticket{" +
            "id=" + id +
            " | createdAt = " + getFormattedDate(createdAt) +
            " | updatedAt = " + getFormattedDate(updatedAt) +
            " | subject = " + StringUtils.abbreviate(StringUtils.remove(subject, "\n"), 20) +
            " | description = " + StringUtils.abbreviate(StringUtils.remove(description, "\n"), 50) +
            " | priority = " + (priority != null ? priority.getPriority() : "") +
            " | status= " + (status != null ? status.getStatus() : "") +
            '}';
  }

}
