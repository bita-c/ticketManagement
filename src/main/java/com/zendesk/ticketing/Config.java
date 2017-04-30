package com.zendesk.ticketing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

  // get this from config file
  public static final String BASE_URL  = "https://bitacrosby.zendesk.com";
  // change with api token
  private static final String USERNAME = "bita.crosby@gmail.com";
  private static final String PASSWORD = "bkhagh@1365";
  public static final String TICKET_PATH =  "/api/v2/tickets/{id}.json";
  public static final String TICKETS_PATH = "/api/v2/tickets.json";

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getInterceptors().add(
            new BasicAuthorizationInterceptor(USERNAME, PASSWORD));
    return restTemplate;


//    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
//    connectionManager.setMaxTotal(100);
//    connectionManager.setDefaultMaxPerRoute(6);
//    template.setRequestFactory(new HttpComponentsClientHttpRequestFactory(HttpClients.custom().setConnectionManager(connectionManager).build()));
//    return restTemplate;
  }





}
