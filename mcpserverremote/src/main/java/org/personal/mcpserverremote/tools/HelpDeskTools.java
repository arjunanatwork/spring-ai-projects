package org.personal.mcpserverremote.tools;

import lombok.RequiredArgsConstructor;
import org.personal.mcpserverremote.entity.HelpDeskTicket;
import org.personal.mcpserverremote.model.TicketRequest;
import org.personal.mcpserverremote.service.HelpDeskTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HelpDeskTools {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpDeskTools.class);

    private final HelpDeskTicketService service;

    @Tool(name = "createTicket", description = "Create the Support Ticket")
    String createTicket(@ToolParam(description = "Create the support ticket") TicketRequest ticketRequest) {
        LOGGER.info("Creating support ticket with details: {}", ticketRequest);
        HelpDeskTicket savedTicket = service.createTicket(ticketRequest);
        LOGGER.info("Ticket created successfully. Ticket ID: {}, Username: {}", savedTicket.getId(), savedTicket.getUsername());
        return "Ticket #" + savedTicket.getId() + " created successfully for user " + savedTicket.getUsername();
    }

    @Tool(description = "Fetch the status of the tickets based on a given username")
    List<HelpDeskTicket> getTicketStatus(String username) {
        LOGGER.info("Fetching tickets for user: {}", username);
        List<HelpDeskTicket> tickets =  service.getTicketsByUsername(username);
        LOGGER.info("Found {} tickets for user: {}", tickets.size(), username);
        return tickets;
    }
}
