package pt.afonsogarcia.parallel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.afonsogarcia.parallel.domain.Room;
import pt.afonsogarcia.parallel.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Room> saveRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomService.saveRoom(room));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Room> getRoom(@PathVariable("id") Long id) {
        return ResponseEntity.ok(roomService.getRoom(id));
    }
}
