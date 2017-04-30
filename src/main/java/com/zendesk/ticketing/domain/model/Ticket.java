package com.zendesk.ticketing.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

  private String url;
  private String id;
  // change to date type
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

  @Override
  public String toString() {
    return "Ticket{" +
            "url='" + url + '\'' +
            ", id=" + id + '\'' +
            ", createdAt=" + id + '\'' +
            ", updateAt=" + updatedAt + '\'' +
            ", subject=" + subject + '\'' +
            ", description=" + description + '\'' +
            ", priority=" + (priority != null ? priority.getPriority() : "") + '\'' +
            ", status=" + (status != null ? status.getStatus() : "") +
            '}';
  }

}
