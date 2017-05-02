##### ZenDesk  Ticket Managemant

###### Build Instructions

1. First clone this repository and cd into it 
3. Build using the gradle wrapper, with:

```
./gradlew build
```

*This will produce a jar file in
uild/libs/ticketManagement-0.1.0.jar*


###### Run the application

* To get all the tickets for the account and page through them, run:

```
java -jar build/libs/ticketManagement-0.1.0.jar tickets
```

This will return 100 tickets per page, if there are more tickets to view, command prompt will advise you.
to see more tickets press enter.



* To view an individual ticket with a given id
```
 java -jar build/libs/ticketManagement-0.1.0.jar ticket <TICKET_ID>
```

replace <TICKET_ID> with the id for ticket you want to view


###### Running Tests

```
./gradlew test
```

