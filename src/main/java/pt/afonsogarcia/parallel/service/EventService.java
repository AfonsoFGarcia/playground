package pt.afonsogarcia.parallel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.afonsogarcia.parallel.domain.Event;
import pt.afonsogarcia.parallel.domain.EventRepository;
import pt.afonsogarcia.parallel.dto.RoomDto;
import pt.afonsogarcia.parallel.dto.SimpleEventDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    private final RoomService roomService;

    @Autowired
    public EventService(EventRepository eventRepository, RoomService roomService) {
        this.eventRepository = eventRepository;
        this.roomService = roomService;
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        eventRepository.findAll().forEach(events::add);
        return events;
    }

    public List<RoomDto> getAllEventsByRoom() {
        List<RoomDto> rooms = Collections.synchronizedList(new ArrayList<>());

        roomService.getAllRooms().parallelStream().forEach(room -> {
            RoomDto roomDto = new RoomDto(room.getId(), room.getName(), room.getCapacity());
            roomDto.addAllEvent(room.getEvents()
                    .parallelStream()
                    .map(event -> new SimpleEventDto(event.getId(), event.getName(), event.getDate()))
                    .collect(Collectors.toList()));
            rooms.add(roomDto);
        });

        return rooms;
    }
}
