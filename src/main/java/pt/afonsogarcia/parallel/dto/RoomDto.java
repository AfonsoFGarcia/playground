package pt.afonsogarcia.parallel.dto;

import java.util.ArrayList;
import java.util.List;

public class RoomDto extends SimpleRoomDto {

    private List<SimpleEventDto> events;

    public RoomDto(Long id, String name, Integer capacity) {
        super(id, name, capacity);
        events = new ArrayList<>();
    }

    public List<SimpleEventDto> getEvents() {
        return events;
    }

    public void addAllEvent(List<SimpleEventDto> events) {
        this.events.addAll(events);
    }

    public void addEvent(SimpleEventDto event) {
        this.events.add(event);
    }
}
