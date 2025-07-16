package org.bookingManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bookingManagement.enums.Genre;

import java.util.HashMap;
import java.util.Map;

@Data
public class LiveShow {
    private Integer id;
    private String name;
    private Genre genre;
    private Map<Integer, Slot> slotMap;

    private static Integer count = 1;

    public LiveShow(String name, Genre genre) {
        this.name = name;
        this.genre = genre;
        this.slotMap = new HashMap<>();
        id = count++;
    }

    public boolean addSlot(int hour, int capacity) {
        if (slotMap.containsKey(hour)) return false;
        slotMap.put(hour, new Slot(hour, capacity));
        return true;
    }

    public Slot getSlot(int hour) {
        return slotMap.get(hour);
    }

    public void updateSlotCapacity(int hour, int capacity){
        slotMap.get(hour).setCapacity(capacity);
    }
}
