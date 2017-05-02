package com.zendesk.ticketing.cliApplication;

import com.zendesk.ticketing.domain.model.Ticket;
import com.zendesk.ticketing.domain.model.TicketPage;
import com.zendesk.ticketing.domain.service.Pager;
import com.zendesk.ticketing.domain.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class CliRunner implements CommandLineRunner{

  @Autowired
  private TicketService ticketService;

  @Autowired
  private Pager pager;

  @Autowired
  private CliHelper cliHelper;

  private static final List<String> commands = Arrays.asList("ticket", "tickets");


  @Override
  public void run(String... args) throws Exception {

    if (args.length == 0) {
      cliHelper.printUsage();

    } else {

      if (!commands.contains(args[0])) {
        cliHelper.printUsage();
      }

      if ("ticket".equals(args[0]) && args.length == 2 ) {

        Ticket ticket = ticketService.getTicket(args[1]);
        System.out.println(ticket.toString());

      } else if ("tickets".equals(args[0]) && args.length == 1) {

        TicketPage ticketPage = ticketService.getTickets(Optional.empty());

        ticketPage.getTickets().forEach(ticket -> System.out.println(ticket.toString()));

        if (pager.isPaginated(ticketPage)) {
          processPaginatedResults(ticketPage);
        }
      }
    }
  }

  private void processPaginatedResults(TicketPage ticketPage) {
    System.out.println("\n To continue scanning, press enter or another key and enter to quit");

    Scanner scanner  = new Scanner(System.in);
    String userCommand = scanner.nextLine();

    while (pager.hasNextPage(ticketPage) && userCommand.equals("")) {

      ticketPage = ticketService.getTickets(Optional.of(pager.getNextPageUrl(ticketPage)));
      ticketPage.getTickets().forEach(ticket -> System.out.println(ticket.toString()));

      if (pager.hasNextPage(ticketPage)) {
        userCommand = scanner.nextLine();
      }
    }
  }
}
