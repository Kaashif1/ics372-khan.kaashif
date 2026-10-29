package edu.metrostate.oodi.hotel;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class RoomType {
    private final String name;
    private int weekdayRate;
    private int weekendRate;
    private final int maximumGuests;
    private final int minimumNights;

    public RoomType(String name, int weekdayRate, int weekendRate, int maximumGuests, int minimumNights) {
        if (name == null || name.isBlank() || weekdayRate < 0 || weekendRate < 0
                || maximumGuests < 1 || minimumNights < 1) {
            throw new IllegalArgumentException("Invalid room type data.");
        }
        this.name = name;
        this.maximumGuests = maximumGuests;
        this.minimumNights = minimumNights;
        this.weekdayRate = weekdayRate;
        this.weekendRate = weekendRate;
    }

    public String getName() { return name; }
    public int getWeekdayRate() { return weekdayRate; }
    public int getWeekendRate() { return weekendRate; }

    public int getRateForDate(LocalDate date) {
        if (date == null) { throw new IllegalArgumentException("Night date is required."); }
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY
            ? weekendRate : weekdayRate;
    }

    public boolean canAccommodate(int guests) {
        return guests >= 1 && guests <= maximumGuests;
    }

    public boolean meetsMinimumStay(int nights) {
        return nights >= minimumNights;
    }

    public void changeRates(int weekdayRate, int weekendRate) {
        if (weekdayRate < 0 || weekendRate < 0) {
            throw new IllegalArgumentException("Rates must not be negative.");
        }
        this.weekdayRate = weekdayRate;
        this.weekendRate = weekendRate;
    }
}
