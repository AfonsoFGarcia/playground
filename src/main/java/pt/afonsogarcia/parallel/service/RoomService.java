package pt.afonsogarcia.parallel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.afonsogarcia.parallel.domain.Room;
import pt.afonsogarcia.parallel.domain.RoomRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        roomRepository.findAll().forEach(rooms::add);
        return rooms;
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room getRoom(Long id) {
        return roomRepository.findOne(id);
    }
}
