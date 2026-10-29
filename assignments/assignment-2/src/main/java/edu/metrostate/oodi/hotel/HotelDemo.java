package edu.metrostate.oodi.hotel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class HotelDemo {
    private static final LocalDate NOV2 = LocalDate.of(2026, 11, 2);

    private static LocalDate nov(int day) { return LocalDate.of(2026, 11, day); }

    private static void showOffers(HotelService hotel, LocalDate start, LocalDate end, int guests) {
        for (Map.Entry<Room, Integer> offer : hotel.findOffers(start, end, guests).entrySet()) {
            Room room = offer.getKey();
            System.out.printf("  Room %d (%s): $%d%n", room.getRoomNumber(),
            room.getRoomType().getName(), offer.getValue());
        }
    }

    private static void showReservation(Reservation reservation) {
        System.out.printf("  Confirmation: %s | Guest: %s | Room: %d | Dates: %s to %s | Total: $%d%n",
            reservation.getConfirmationNumber(), reservation.getGuest().getName(),
            reservation.getRoom().getRoomNumber(), reservation.getCheckInDate(),
            reservation.getCheckOutDate(), reservation.calculateTotal());
    }

    private static void showBill(Bill bill) {
        System.out.printf("  Bill %s (issued %s):%n", bill.getBillNumber(), bill.getIssuedOn());
        for (Map.Entry<LocalDate, Integer> charge : bill.getNightlyCharges().entrySet()) {
            System.out.printf("  Night %s: $%d%n", charge.getKey(), charge.getValue());
        }
        System.out.printf("  Total due: $%d%n", bill.getTotal());
    }
    public static void main(String[] args) {
        ReservationManager reservationManager = new ReservationManager();
        RoomCatalog catalog = new RoomCatalog(reservationManager);
        GuestRegistry registry = new GuestRegistry();
        HotelService hotel = new HotelService(registry, catalog, reservationManager);
        RoomType standard = new RoomType("Standard", 129, 159, 2, 1);
        RoomType deluxe = new RoomType("Deluxe", 179, 219, 3, 1);
        RoomType suite = new RoomType("Suite", 289, 349, 4, 2);
        catalog.addRoom(new Room(101, 1, standard));
        catalog.addRoom(new Room(102, 1, standard));
        catalog.addRoom(new Room(201, 2, deluxe));
        catalog.addRoom(new Room(301, 3, suite));

        System.out.println("Step 1: November 2 - offers for Nov 12-15, 2 guests");
        showOffers(hotel, nov(12), nov(15), 2);

        System.out.println("Step 2: November 2 - reserve Deluxe room 201 for Ana");
        Reservation ana = hotel.reserveRoom(201, nov(12), nov(15), 2,
                "Ana Okafor", "ana.okafor@example.com", "612-555-0142", NOV2);
        showReservation(ana);

        System.out.println("Step 3: November 2 - reject overlapping booking for Marcus");
        try {
            hotel.reserveRoom(201, nov(14), nov(16), 2, "Marcus Lindqvist",
                    "marcus.l@example.com", "651-555-0187", NOV2);
            System.out.println("  ERROR: overlapping reservation unexpectedly succeeded");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            System.out.println("  Rejected: " + ex.getMessage());
        }

        System.out.println("Step 4: November 2 - reserve back-to-back Nov 15-17 for Marcus");
        Reservation marcus = hotel.reserveRoom(201, nov(15), nov(17), 2,
                "Marcus Lindqvist", "marcus.l@example.com", "651-555-0187", NOV2);
        showReservation(marcus);

        System.out.println("Step 5: November 2 - offers for Nov 20-21, 2 guests");
        showOffers(hotel, nov(20), nov(21), 2);

        System.out.println("Step 6: November 3 - change Deluxe weekday/weekend rates");
        deluxe.changeRates(199, 239);
        System.out.printf("  Deluxe now weekday $%d / weekend $%d%n",
                deluxe.getWeekdayRate(), deluxe.getWeekendRate());

        System.out.println("Step 7: November 3 - offers for Nov 19-21, 3 guests");
        showOffers(hotel, nov(19), nov(21), 3);


       System.out.println("Step 8: November 5 - cancel Marcus, then offers for Nov 15-17");
       System.out.printf("  Guest: %s | Room: %d | Dates: %s to %s%n",
        marcus.getGuest().getName(),
        marcus.getRoom().getRoomNumber(),
        marcus.getCheckInDate(),
        marcus.getCheckOutDate());

       Reservation cancelled = hotel.cancelReservation(marcus.getConfirmationNumber());
       System.out.println("  Cancelled " + cancelled.getConfirmationNumber()
        + " (" + cancelled.getStatus() + ")");
       showOffers(hotel, nov(15), nov(17), 2);


        System.out.println("Step 9: November 12 at 3:10 PM - Ana checks in");
        int checkInRoom = hotel.checkIn(ana.getConfirmationNumber(),
                LocalDateTime.of(2026, 11, 12, 15, 10));
        System.out.println("  Checked in to room " + checkInRoom);

        System.out.println("Step 10: November 15 at 10:45 AM - Ana checks out; room needs repair");
        Bill bill = hotel.checkOut(201, LocalDateTime.of(2026, 11, 15, 10, 45));
        showBill(bill);
        hotel.reportRoomNeedsRepair(201);
        System.out.println("  Room 201 in service: " + catalog.getRoom(201).isInService());

        System.out.println("Step 11: November 15 at 11:00 AM - reject check-in of cancelled Marcus");
        try {
            hotel.checkIn(marcus.getConfirmationNumber(), LocalDateTime.of(2026, 11, 15, 11, 0));
            System.out.println("  ERROR: cancelled reservation unexpectedly checked in");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            System.out.println("  Rejected: " + ex.getMessage());
        }

        System.out.println("Step 12: November 15 - offers for Nov 15-17 after repair flag");
        showOffers(hotel, nov(15), nov(17), 2);
    }
}
