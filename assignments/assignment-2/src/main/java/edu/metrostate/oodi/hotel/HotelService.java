package edu.metrostate.oodi.hotel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HotelService {
    private final GuestRegistry guestRegistry;
    private final RoomCatalog roomCatalog;
    private final ReservationManager reservationManager;
    private int nextBillNumber = 5001;

    public HotelService(GuestRegistry guestRegistry, RoomCatalog roomCatalog,
        ReservationManager reservationManager) {
        if (guestRegistry == null || roomCatalog == null || reservationManager == null) {
            throw new IllegalArgumentException("Hotel collaborators must be present.");
        }
        this.roomCatalog = roomCatalog;

        this.guestRegistry = guestRegistry;
        
        this.reservationManager = reservationManager;
    }

    public Map<Room, Integer> findOffers(LocalDate checkIn, LocalDate checkOut, int guests) {
        List<Room> available = roomCatalog.findAvailableRooms(checkIn, checkOut, guests);
        if (available.isEmpty()) {
            throw new IllegalStateException("No room is free for those dates and guest count.");
        }
        Map<Room, Integer> offers = new LinkedHashMap<>();
        for (Room room : available) {
            int total = 0;
            RoomType type = room.getRoomType();
            for (LocalDate date = checkIn; date.isBefore(checkOut); date = date.plusDays(1)) {
                total = Math.addExact(total, type.getRateForDate(date));
            }
            offers.put(room, total);
        }
    return offers;
    }

    public Reservation reserveRoom(int roomNumber, LocalDate checkIn, LocalDate checkOut,
                                   int guests, String name, String email, String phone, LocalDate madeOn) {
        if (checkIn == null || checkOut == null || !checkIn.isBefore(checkOut) || guests < 1
                || madeOn == null) {
            throw new IllegalArgumentException("Check-in must precede checkout and guests must be positive.");
        }
        Room room = roomCatalog.getRoom(roomNumber);
        Guest guest = guestRegistry.findOrCreateGuest(name, email, phone);
        RoomType type = room.getRoomType();
        int nights = Math.toIntExact(ChronoUnit.DAYS.between(checkIn, checkOut));
        if (!room.isInService() || !type.canAccommodate(guests) || !type.meetsMinimumStay(nights)) {
            throw new IllegalStateException("Room " + roomNumber + " does not meet service, capacity, or minimum-night rules.");
        }
        // Recheck immediately before recording, because a prior search is only a quote.
        LocalDate conflict = reservationManager.getFirstUnavailableNight(room, checkIn, checkOut);
        if (conflict != null) {
            throw new IllegalStateException("Room " + roomNumber + " is not free for the night of " + conflict + ".");
        }
        Reservation reservation = new Reservation(reservationManager.nextConfirmationNumber(),
                guest, room, checkIn, checkOut, guests, madeOn);
        reservation.recordBookedRates();
        reservationManager.addReservation(reservation);
        return reservation;
    }

    public Reservation cancelReservation(String confirmationNumber) {
        Reservation reservation = reservationManager.findByConfirmationNumber(confirmationNumber);
        reservation.cancel();
        return reservation;
    }

    public int checkIn(String confirmationNumber, LocalDateTime arrivalTime) {
        if (arrivalTime == null) { throw new IllegalArgumentException("Arrival time is required."); }
        Reservation reservation = reservationManager.findByConfirmationNumber(confirmationNumber);
        if (reservation.getStatus() != ReservationStatus.CONFIRMED
                || !reservation.getCheckInDate().equals(arrivalTime.toLocalDate())) {
            throw new IllegalStateException("Cannot check in " + confirmationNumber
                    + ": status is " + reservation.getStatus()
                    + "; check-in date is " + reservation.getCheckInDate() + ".");
        }
        Stay stay = new Stay(reservation, arrivalTime);
        reservation.checkIn(stay);
        return reservation.getRoom().getRoomNumber();
    }

    public Bill checkOut(int roomNumber, LocalDateTime departureTime) {
        if (departureTime == null) { throw new IllegalArgumentException("Departure time is required."); }
        Reservation reservation = reservationManager.findActiveForRoom(roomNumber);
        Stay stay = reservation.getStay();
        stay.endStay(departureTime);
        Bill bill = stay.issueBill("BILL-" + nextBillNumber++, departureTime.toLocalDate());
        reservation.complete();
        return bill;
    }

    public void reportRoomNeedsRepair(int roomNumber) {
        roomCatalog.getRoom(roomNumber).setInService(false);
    } }
