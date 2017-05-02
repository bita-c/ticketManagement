package com.zendesk.ticketing.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticatedRestTemplate {

  @Autowired
  private Config config;

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getInterceptors().add(
            new BasicAuthorizationInterceptor(config.getUsername(), config.getPassword()));
    return restTemplate;
  }

}
