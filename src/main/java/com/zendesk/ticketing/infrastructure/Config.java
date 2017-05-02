package com.zendesk.ticketing.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class Config {

  @Value( "${rest.client.url}")
  private String baseUrl;

  @Value( "${rest.client.username}")
  private String username;

  @Value( "${rest.client.password}")
  private String password;

  @Value( "${rest.client.path.ticket}")
  public String ticketPath;

  @Value( "${rest.client.path.tickets}")
  public String ticketsPath;


  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public String getTicketPath() {
    return ticketPath;
  }

  public String getTicketsPath() {
    return ticketsPath;
  }

}
