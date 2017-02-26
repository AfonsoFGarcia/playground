package pt.afonsogarcia.parallel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.afonsogarcia.parallel.domain.Event;
import pt.afonsogarcia.parallel.dto.RoomDto;
import pt.afonsogarcia.parallel.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/rooms")
    @ResponseBody
    public ResponseEntity<List<RoomDto>> getAllEventsByRoom() {
        return ResponseEntity.ok(eventService.getAllEventsByRoom());
    }
}
