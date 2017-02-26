package pt.afonsogarcia.parallel.dto;

import java.util.Calendar;

public class SimpleEventDto {

    private Long id;

    private String name;

    private Calendar date;

    public SimpleEventDto(Long id, String name, Calendar date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
