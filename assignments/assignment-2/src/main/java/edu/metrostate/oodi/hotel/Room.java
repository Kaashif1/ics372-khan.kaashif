package edu.metrostate.oodi.hotel;

public class Room {
    private final int roomNumber;
    private final int floor;
    private boolean inService;
    private final RoomType roomType;

    public Room(int roomNumber, int floor, RoomType roomType) {
        if (roomNumber < 1 || floor < 0 || roomType == null) {
            throw new IllegalArgumentException("Invalid physical room data.");
        }
        this.roomNumber = roomNumber;
        this.inService = true;
        this.floor = floor;
        this.roomType = roomType;
    }

    public int getRoomNumber() { return roomNumber; }
    public int getFloor() { return floor; }
    public boolean isInService() { return inService; }
    public RoomType getRoomType() { return roomType; }
    public void setInService(boolean inService) { this.inService = inService; }
}
