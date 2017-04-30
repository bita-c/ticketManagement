package com.zendesk.ticketing.application;

import com.zendesk.ticketing.domain.model.Ticket;
import com.zendesk.ticketing.domain.service.TicketService;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ApplicationRunner implements CommandLineRunner{

  @Autowired
  private TicketService restClientService;

  private static final List<String> commands = Arrays.asList("ticket", "tickets");


  @Override
  public void run(String... args) throws Exception {

    if (args.length == 0) {
      printUsage();

    } else {

      if (!commands.contains(args[0])) {
        printUsage();
      }

      if ("ticket".equals(args[0])) {

        if (args.length != 2 ) {
          printUsage();
        }
        Ticket ticket = restClientService.getTicket(args[1]);
        System.out.println(ticket.toString());

      } else {

        List<Ticket> tickets = restClientService.getTicketsWithPaging();
        tickets.forEach(ticket -> System.out.println(ticket.toString()));
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
}
