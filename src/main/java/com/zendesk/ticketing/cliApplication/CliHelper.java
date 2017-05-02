package com.zendesk.ticketing.cliApplication;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.springframework.stereotype.Component;

@Component
public class CliHelper {

  public void printUsage() {

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
