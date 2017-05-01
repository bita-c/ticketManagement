package com.zendesk.ticketing.application;

import com.zendesk.ticketing.domain.model.Ticket;
import com.zendesk.ticketing.domain.model.TicketPage;
import com.zendesk.ticketing.domain.service.Pager;
import com.zendesk.ticketing.domain.service.TicketService;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ApplicationRunner implements CommandLineRunner{

  @Autowired
  private TicketService restClientService;

  @Autowired
  private Pager pager;

  private static final List<String> commands = Arrays.asList("ticket", "tickets");


  @Override
  public void run(String... args) throws Exception {

    if (args.length == 0) {
      printUsage();

    } else {

      if (!commands.contains(args[0])) {
        printUsage();
      }

      if ("ticket".equals(args[0]) && args.length == 2 ) {

        Ticket ticket = restClientService.getTicket(args[1]);
        System.out.println(ticket.toString());

      } else if ("tickets".equals(args[0]) && args.length == 1) {

        TicketPage ticketPage = restClientService.getTickets(Optional.empty());

        ticketPage.getTickets().forEach(ticket -> System.out.println(ticket.toString()));

        if (pager.isPaginated(ticketPage)) {
          processPaginatedResults(ticketPage);
        }
      }
    }
  }

  private void printUsage() {

    Options options = new Options();
    HelpFormatter formatter = new HelpFormatter();

    Option getSingleTicket = Option.builder("ticket").hasArg().argName("id")
            .desc("get Ticket with id").build();
    Option getAllTickets = Option.builder("tickets").desc("get all Tickets").build();

    options.addOption(getSingleTicket);
    options.addOption(getAllTickets);

    formatter.printHelp("sample", options);

  }

  private void processPaginatedResults(TicketPage ticketPage) {
    System.out.println("\n To continue scanning, press enter or another key and enter to quit ");

    Scanner scanner  = new Scanner(System.in);
    String userCommand = scanner.nextLine();

    while (pager.hasNextPage(ticketPage) && userCommand.equals("")) {

      ticketPage = restClientService.getTickets(Optional.of(pager.getNextPageUrl(ticketPage)));
      ticketPage.getTickets().forEach(ticket -> System.out.println(ticket.toString()));

      if (pager.hasNextPage(ticketPage)) {
        userCommand = scanner.nextLine();
      }
    }
  }
}
