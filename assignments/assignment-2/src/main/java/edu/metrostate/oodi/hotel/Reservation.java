package edu.metrostate.oodi.hotel;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Reservation {
    private final String confirmationNumber;
    private final Guest guest;
    private final Room room;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final int numberOfGuests;
    private final LocalDate madeOn;
    private final Map<LocalDate, Integer> bookedRates = new LinkedHashMap<>();
    private ReservationStatus status = ReservationStatus.CONFIRMED;
    private Stay stay;

    public Reservation(String confirmationNumber, Guest guest, Room room, LocalDate checkInDate,
                       LocalDate checkOutDate, int numberOfGuests, LocalDate madeOn) {
        if (confirmationNumber == null || confirmationNumber.isBlank() || guest == null
                || room == null || checkInDate == null || checkOutDate == null || madeOn == null
                || !checkInDate.isBefore(checkOutDate) || numberOfGuests < 1) {
            throw new IllegalArgumentException("Invalid reservation data or date range.");
        }
        this.confirmationNumber = confirmationNumber;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.madeOn = madeOn;
    }

    public String getConfirmationNumber() { return confirmationNumber; }
    public Guest getGuest() { return guest; }
    public Room getRoom() { return room; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public int getNumberOfGuests() { return numberOfGuests; }
    public LocalDate getMadeOn() { return madeOn; }
    public ReservationStatus getStatus() { return status; }
    public Stay getStay() { return stay; }
    public Map<LocalDate, Integer> getBookedRates() {
        return Collections.unmodifiableMap(bookedRates);
    }

    public void recordBookedRates() {
        if (!bookedRates.isEmpty()) {
        throw new IllegalStateException("Booked nightly rates cannot be changed.");
        }
        RoomType type = room.getRoomType();
        for (LocalDate night = checkInDate; night.isBefore(checkOutDate); night = night.plusDays(1)) {
        bookedRates.put(night, type.getRateForDate(night));
        }
    }

    public int calculateTotal() {
        if (bookedRates.isEmpty()) {
            throw new IllegalStateException("Reservation has no booked nightly rates.");
        }
        int total = 0;
        for (int amount : bookedRates.values()) { total = Math.addExact(total, amount); }
        return total;
    }

    public boolean overlaps(Room otherRoom, LocalDate start, LocalDate end) {
        if (!checkInDate.isBefore(checkOutDate) || !start.isBefore(end)) {
            throw new IllegalArgumentException("Invalid reservation date range.");
        }
         return room == otherRoom && status != ReservationStatus.CANCELLED
                && start.isBefore(checkOutDate) && checkInDate.isBefore(end);
    }

    public void cancel() {
        if (status != ReservationStatus.CONFIRMED) {
            throw new IllegalStateException("Cannot cancel reservation " + confirmationNumber
                    + ": status is " + status + ".");
        }
        status = ReservationStatus.CANCELLED;
    }

    public void checkIn(Stay stay) {
        if (status != ReservationStatus.CONFIRMED || stay == null) {
            throw new IllegalStateException("Reservation " + confirmationNumber
                    + " cannot check in: status is " + status + ".");
        }
        this.stay = stay;
        this.status = ReservationStatus.CHECKED_IN;
    }

    public void complete() {
        if (status != ReservationStatus.CHECKED_IN || stay == null || stay.getBill() == null) {
            throw new IllegalStateException("Cannot complete reservation: checked-in stay and bill required.");
        }
        status = ReservationStatus.COMPLETED;
    }
}
