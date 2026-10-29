package edu.metrostate.oodi.hotel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Stay {
    private final Reservation reservation;
    private final LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Bill bill;

    public Stay(Reservation reservation, LocalDateTime startedAt) {
        if (reservation == null || startedAt == null) {
            throw new IllegalArgumentException("Reservation and arrival time are required.");
        }
        this.reservation = reservation;

        this.startedAt = startedAt;
    }

    public LocalDateTime getStartedAt() { return startedAt; }
    public LocalDateTime getEndedAt() { return endedAt; }
    public Bill getBill() { return bill; }

    public void endStay(LocalDateTime time) {
        if (endedAt != null) { throw new IllegalStateException("The stay has already ended."); }
        if (time == null || time.isBefore(startedAt)) {
            throw new IllegalArgumentException("Checkout time must be after arrival.");
        }
    endedAt = time;
    }

    public Bill issueBill(String billNumber, LocalDate issuedOn) {
        if (endedAt == null) { throw new IllegalStateException("Cannot bill a stay before checkout."); }
        if (bill != null) { throw new IllegalStateException("Bill already issued for this stay."); }
        bill = new Bill(billNumber, issuedOn, reservation.getBookedRates(), reservation.calculateTotal());
        return bill;
    }
    
}
