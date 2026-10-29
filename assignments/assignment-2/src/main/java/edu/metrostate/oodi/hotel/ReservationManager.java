package edu.metrostate.oodi.hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationManager {
    private final List<Reservation> reservations = new ArrayList<>();
    private int nextConfirmationNumber = 1001;

    public boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        return getFirstUnavailableNight(room, checkIn, checkOut) == null;
    }

    public LocalDate getFirstUnavailableNight(Room room, LocalDate checkIn, LocalDate checkOut) {
        if (room == null || checkIn == null || checkOut == null || !checkIn.isBefore(checkOut)) {
            throw new IllegalArgumentException("Check-in must be before checkout.");
        }
        for (LocalDate night = checkIn; night.isBefore(checkOut); night = night.plusDays(1)) {
            for (Reservation reservation : reservations) {
                if (reservation.overlaps(room, night, night.plusDays(1))) { return night; }
            }
        }
    return null;
    }

    public String nextConfirmationNumber() {
        return "HV-" + nextConfirmationNumber++;
    }

    public void addReservation(Reservation reservation) {
        if (reservation == null) { throw new IllegalArgumentException("Reservation is required."); }
        if (getFirstUnavailableNight(reservation.getRoom(), reservation.getCheckInDate(),
            reservation.getCheckOutDate()) != null) {
        throw new IllegalStateException("Cannot record reservation: room is no longer free.");
        }
        reservations.add(reservation);
    }

    public Reservation findByConfirmationNumber(String confirmationNumber) {
        for (Reservation reservation : reservations) {
            if (reservation.getConfirmationNumber().equals(confirmationNumber)) { return reservation; }
        }
        throw new IllegalArgumentException("No reservation has confirmation number " + confirmationNumber + ".");
    }

    public Reservation findActiveForRoom(int roomNumber) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().getRoomNumber() == roomNumber
                    && reservation.getStatus() == ReservationStatus.CHECKED_IN) {
        return reservation;
            }
        }
        throw new IllegalStateException("No stay in progress in room " + roomNumber + ".");
    }
}
