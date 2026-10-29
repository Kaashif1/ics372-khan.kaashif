package edu.metrostate.oodi.hotel;

import java.util.LinkedHashMap;
import java.util.Map;

public class GuestRegistry {
    private final Map<String, Guest> guestsByEmail = new LinkedHashMap<>();

    public Guest findOrCreateGuest(String name, String email, String phone) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Guest email cannot be empty.");
        }
        
        String key = email.trim().toLowerCase(java.util.Locale.ROOT);
        Guest existing = guestsByEmail.get(key);
        if (existing != null) { return existing; }
        Guest guest = new Guest(name, email.trim(), phone);
        guestsByEmail.put(key, guest);
        return guest;
    }
}
