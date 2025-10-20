package org.personal.mcpserverremote.service;

import lombok.RequiredArgsConstructor;
import org.personal.mcpserverremote.entity.HelpDeskTicket;
import org.personal.mcpserverremote.model.TicketRequest;
import org.personal.mcpserverremote.repository.HelpDeskTicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HelpDeskTicketService {

    private final HelpDeskTicketRepository helpDeskTicketRepository;

    public HelpDeskTicket createTicket(TicketRequest ticketInput) {
        HelpDeskTicket ticket = HelpDeskTicket.builder()
                .issue(ticketInput.issue())
                .username(ticketInput.username())
                .status("OPEN")
                .createdAt(LocalDateTime.now())
                .eta(LocalDateTime.now().plusDays(7))
                .build();
        return helpDeskTicketRepository.save(ticket);
    }

    public List<HelpDeskTicket> getTicketsByUsername(String username) {
        return helpDeskTicketRepository.findByUsername(username);
    }

}
