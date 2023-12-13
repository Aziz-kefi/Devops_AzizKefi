package tn.esprit.eventsproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.eventsproject.dto.EventDTO;
import tn.esprit.eventsproject.dto.LogisticsDTO;  // Added LogisticsDTO
import tn.esprit.eventsproject.dto.ParticipantDTO; // Added ParticipantDTO
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.services.IEventServices;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("event")
@RestController
public class EventRestController {
    private final IEventServices eventServices;

    @PostMapping("/addPart")
    public ParticipantDTO addParticipant(@RequestBody ParticipantDTO participantDTO) {  // Changed input and output to ParticipantDTO
        // Validate participantDTO and convert to entity if needed
        Participant participant = convertDTOToParticipant(participantDTO);
        return convertParticipantToDTO(eventServices.addParticipant(participant));  // Convert the result back to DTO
    }

    @PostMapping("/addEvent/{id}")
    public EventDTO addEventPart(@RequestBody EventDTO eventDTO, @PathVariable("id") int idPart) {
        Event event = convertDTOToEvent(eventDTO);
        return convertEventToDTO(eventServices.addAffectEvenParticipant(event, idPart));
    }

    @PostMapping("/addEvent")
    public EventDTO addEvent(@RequestBody EventDTO eventDTO) {
        Event event = convertDTOToEvent(eventDTO);
        return convertEventToDTO(eventServices.addAffectEvenParticipant(event));
    }

    @PutMapping("/addAffectLog/{description}")
    public LogisticsDTO addAffectLog(@RequestBody LogisticsDTO logisticsDTO, @PathVariable("description") String descriptionEvent) {
        Logistics logistics = convertDTOToLogistics(logisticsDTO);
        return convertLogisticsToDTO(eventServices.addAffectLog(logistics, descriptionEvent));
    }

    private EventDTO convertEventToDTO(Event event) {
        // Implement conversion logic from Event to EventDTO
        EventDTO eventDTO = new EventDTO();
        // Set fields in eventDTO
        return eventDTO;
    }

    private ParticipantDTO convertParticipantToDTO(Participant participant) {
        // Implement conversion logic from Participant to ParticipantDTO
        ParticipantDTO participantDTO = new ParticipantDTO();
        // Set fields in participantDTO
        return participantDTO;
    }

    private Event convertDTOToEvent(EventDTO eventDTO) {
        // Implement conversion logic from EventDTO to Event
        Event event = new Event();
        // Set fields in event
        return event;
    }

    private Participant convertDTOToParticipant(ParticipantDTO participantDTO) {
        // Implement conversion logic from ParticipantDTO to Participant
        Participant participant = new Participant();
        // Set fields in participant
        return participant;
    }

    private LogisticsDTO convertLogisticsToDTO(Logistics logistics) {
        // Implement conversion logic from Logistics to LogisticsDTO
        LogisticsDTO logisticsDTO = new LogisticsDTO();
        // Set fields in logisticsDTO
        return logisticsDTO;
    }

    private Logistics convertDTOToLogistics(LogisticsDTO logisticsDTO) {
        // Implement conversion logic from LogisticsDTO to Logistics
        Logistics logistics = new Logistics();
        // Set fields in logistics
        return logistics;
    }

    @GetMapping("/getLogs/{d1}/{d2}")
    public List<LogisticsDTO> getLogistiquesDates(@PathVariable("d1") LocalDate date_debut, @PathVariable("d2") LocalDate date_fin) {
        List<Logistics> logisticsList = eventServices.getLogisticsDates(date_debut, date_fin);
        return convertLogisticsListToDTOList(logisticsList);
    }

    private List<LogisticsDTO> convertLogisticsListToDTOList(List<Logistics> logisticsList) {
        // Implement conversion logic from List<Logistics> to List<LogisticsDTO>
        // Iterate through logisticsList and convert each Logistics to LogisticsDTO
        return List.of();  // Replace with actual conversion logic
    }
}