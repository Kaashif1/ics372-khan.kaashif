package edu.metrostate.oodi.hotel;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bill {
    private final String billNumber;
    private final LocalDate issuedOn;
    private final Map<LocalDate, Integer> nightlyCharges;
    private final int total;

    public Bill(String billNumber, LocalDate issuedOn, Map<LocalDate, Integer> nightlyCharges, int total) {
        if (billNumber == null || billNumber.isBlank() || issuedOn == null || nightlyCharges == null
             || nightlyCharges.isEmpty() || total < 0) {
            throw new IllegalArgumentException("Invalid bill data.");
        }
    this.billNumber = billNumber;
        this.issuedOn = issuedOn;
        this.nightlyCharges = Collections.unmodifiableMap(new LinkedHashMap<>(nightlyCharges));
        this.total = total;
    }

    public String getBillNumber() { return billNumber; }
    public LocalDate getIssuedOn() { return issuedOn; }
    public Map<LocalDate, Integer> getNightlyCharges() { return nightlyCharges; }
    public int getTotal() { return total; } }
