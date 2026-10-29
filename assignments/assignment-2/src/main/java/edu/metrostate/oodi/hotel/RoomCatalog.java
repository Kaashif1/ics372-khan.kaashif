package edu.metrostate.oodi.hotel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class RoomCatalog {
    private final List<Room> rooms = new ArrayList<>();
    private final ReservationManager reservationManager;

    public RoomCatalog(ReservationManager reservationManager) {
        if (reservationManager == null) {
            throw new IllegalArgumentException("Reservation manager is required.");
        }
        this.reservationManager = reservationManager;
    }

    public void addRoom(Room room) {
        if (room == null) { throw new IllegalArgumentException("Room is required."); }
    for (Room existing : rooms) {
            if (existing.getRoomNumber() == room.getRoomNumber()) {
                throw new IllegalArgumentException("Duplicate room number " + room.getRoomNumber() + ".");
            }
        }
        rooms.add(room);
    }

    public Room getRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) { return room; }
        }
        throw new IllegalArgumentException("No room has number " + roomNumber + ".");
    }

    public List<Room> findAvailableRooms(LocalDate checkIn, LocalDate checkOut, int guests) {
    if (checkIn == null || checkOut == null || !checkIn.isBefore(checkOut) || guests < 1) {
            throw new IllegalArgumentException("Dates and positive guest count are required.");
        }
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        List<Room> eligible = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isInService()) { continue; }
            RoomType type = room.getRoomType();
            if (type.canAccommodate(guests) && type.meetsMinimumStay(Math.toIntExact(nights))
                    && reservationManager.isRoomAvailable(room, checkIn, checkOut)) {
                eligible.add(room);
            }
        }
     return eligible;
    }
}
