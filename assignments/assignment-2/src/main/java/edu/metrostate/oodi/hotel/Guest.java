package edu.metrostate.oodi.hotel;

public class Guest {
    private final String name;
    private final String email;
    private final String phone;

    public Guest(String name, String email, String phone) {
        if (name == null || name.isBlank() || email == null || email.isBlank()
                || phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("A guest requires a name, email, and phone.");
        }
        this.name = name;
        this.phone = phone;
        this.email = email;
        
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; } }
