# Assignment 2: Kaashif Khan 

## Deliverable 1: Mapping Table
Entity      | Verdict   | Becomes      | If no class, where it went 
|---|---|---|---|
Guest       | One class | `Guest`        | - |
Room Type   | One class | `RoomType`     | - |
Room        | One class | `Room`         | - |
Reservation | One class | `Reservation`  | - |
Stay        | One class | `Stay`         | - |
Bill        | One class | `Bill`         | - |

**Explanation:** Each of the six entities becomes one class because each one represents a distinct part of the hotel system with its own information and responsibilities. I decided to keep RoomType as one class because standard, deluxe, and suite follow the same rules and only differ in their attribute values. That's why separate subclasses would add unnecessary complexity, none of the entities needed to be split or removed because each still serves a purpose in the software.

## Deliverable 2: Class Model

### Class Diagram 

```mermaid
classDiagram
    direction TB

    class Guest {
        -name: String
        -email: String
        -phone: String
        +Guest(name: String, email: String, phone: String)
        +getName(): String
        +getEmail(): String
        +getPhone(): String
    }

    class GuestRegistry {
        -guestsByEmail: Map~String,Guest~
        +findOrCreateGuest(name: String, email: String, phone: String): Guest
    }

    class RoomType {
        -name: String
        -weekdayRate: int
        -weekendRate: int
        -maximumGuests: int
        -minimumNights: int
        +RoomType(name: String, weekdayRate: int, weekendRate: int, maximumGuests: int, minimumNights: int)
        +getName(): String
        +getWeekdayRate(): int
        +getWeekendRate(): int
        +getRateForDate(date: LocalDate): int
        +canAccommodate(guests: int): boolean
        +meetsMinimumStay(nights: int): boolean
        +changeRates(weekdayRate: int, weekendRate: int): void
    }

    class Room {
        -roomNumber: int
        -floor: int
        -inService: boolean
        -roomType: RoomType
        +Room(roomNumber: int, floor: int, roomType: RoomType)
        +getRoomNumber(): int
        +getFloor(): int
        +isInService(): boolean
        +getRoomType(): RoomType
        +setInService(inService: boolean): void
    }

    class ReservationStatus {
        <<enumeration>>
        CONFIRMED
        CANCELLED
        CHECKED_IN
        COMPLETED
    }

    class Reservation {
        -confirmationNumber: String
        -guest: Guest
        -room: Room
        -checkInDate: LocalDate
        -checkOutDate: LocalDate
        -numberOfGuests: int
        -madeOn: LocalDate
        -bookedRates: Map~LocalDate,Integer~
        -status: ReservationStatus
        -stay: Stay
        +Reservation(confirmationNumber: String, guest: Guest, room: Room, checkInDate: LocalDate, checkOutDate: LocalDate, numberOfGuests: int, madeOn: LocalDate)
        +getConfirmationNumber(): String
        +getGuest(): Guest
        +getRoom(): Room
        +getCheckInDate(): LocalDate
        +getCheckOutDate(): LocalDate
        +getNumberOfGuests(): int
        +getMadeOn(): LocalDate
        +getStatus(): ReservationStatus
        +getStay(): Stay
        +getBookedRates(): Map~LocalDate,Integer~
        +recordBookedRates(): void
        +calculateTotal(): int
        +overlaps(otherRoom: Room, start: LocalDate, end: LocalDate): boolean
        +cancel(): void
        +checkIn(stay: Stay): void
        +complete(): void
    }

    class ReservationManager {
        -reservations: List~Reservation~
        -nextConfirmationNumber: int
        +isRoomAvailable(room: Room, checkIn: LocalDate, checkOut: LocalDate): boolean
        +getFirstUnavailableNight(room: Room, checkIn: LocalDate, checkOut: LocalDate): LocalDate
        +nextConfirmationNumber(): String
        +addReservation(reservation: Reservation): void
        +findByConfirmationNumber(confirmationNumber: String): Reservation
        +findActiveForRoom(roomNumber: int): Reservation
    }

    class RoomCatalog {
        -rooms: List~Room~
        -reservationManager: ReservationManager
        +RoomCatalog(reservationManager: ReservationManager)
        +addRoom(room: Room): void
        +getRoom(roomNumber: int): Room
        +findAvailableRooms(checkIn: LocalDate, checkOut: LocalDate, guests: int): List~Room~
    }

    class Stay {
        -reservation: Reservation
        -startedAt: LocalDateTime
        -endedAt: LocalDateTime
        -bill: Bill
        +Stay(reservation: Reservation, startedAt: LocalDateTime)
        +getStartedAt(): LocalDateTime
        +getEndedAt(): LocalDateTime
        +getBill(): Bill
        +endStay(time: LocalDateTime): void
        +issueBill(billNumber: String, issuedOn: LocalDate): Bill
    }

    class Bill {
        -billNumber: String
        -issuedOn: LocalDate
        -nightlyCharges: Map~LocalDate,Integer~
        -total: int
        +Bill(billNumber: String, issuedOn: LocalDate, nightlyCharges: Map~LocalDate,Integer~, total: int)
        +getBillNumber(): String
        +getIssuedOn(): LocalDate
        +getNightlyCharges(): Map~LocalDate,Integer~
        +getTotal(): int
    }

    class HotelService {
        -guestRegistry: GuestRegistry
        -roomCatalog: RoomCatalog
        -reservationManager: ReservationManager
        -nextBillNumber: int
        +HotelService(guestRegistry: GuestRegistry, roomCatalog: RoomCatalog, reservationManager: ReservationManager)
        +findOffers(checkIn: LocalDate, checkOut: LocalDate, guests: int): Map~Room,Integer~
        +reserveRoom(roomNumber: int, checkIn: LocalDate, checkOut: LocalDate, guests: int, name: String, email: String, phone: String, madeOn: LocalDate): Reservation
        +cancelReservation(confirmationNumber: String): Reservation
        +checkIn(confirmationNumber: String, arrivalTime: LocalDateTime): int
        +checkOut(roomNumber: int, departureTime: LocalDateTime): Bill
        +reportRoomNeedsRepair(roomNumber: int): void
    }

    class HotelDemo {
        -NOV2: LocalDate
        -nov(day: int): LocalDate
        -showOffers(hotel: HotelService, start: LocalDate, end: LocalDate, guests: int): void
        -showReservation(reservation: Reservation): void
        -showBill(bill: Bill): void
        +main(args: String[]): void
    }

    GuestRegistry "1" --> "0..*" Guest : manages
    RoomCatalog "1" --> "0..*" Room : contains
    RoomCatalog "1" --> "1" ReservationManager : checks availability
    RoomCatalog "1" --> "0..*" RoomType : checks requirements
    Room "0..*" --> "1" RoomType : has type

    ReservationManager "1" --> "0..*" Reservation : stores
    Reservation "0..*" --> "1" Guest : belongs to
    Reservation "0..*" --> "1" Room : reserves
    Reservation "0..*" --> "1" RoomType : reads rates
    Reservation "0..*" --> "1" ReservationStatus : has status
    Stay "0..1" --> "1" Reservation : belongs to
    Stay "1" --> "0..1" Bill : creates

    HotelService "1" --> "1" GuestRegistry : uses
    HotelService "1" --> "1" RoomCatalog : uses
    HotelService "1" --> "1" ReservationManager : uses
    HotelService "1" --> "0..*" Reservation : manages
    HotelService "1" --> "0..*" Room : accesses
    HotelService "1" --> "0..*" RoomType : gets prices
    HotelService "1" --> "0..*" Stay : handles checkout

    HotelDemo "1" --> "1" HotelService : tests
```

### Responsibilities

- **Guest**: Responsible for: retaining one guest's identifying contact details. Traces to: entity Guest.
- **GuestRegistry**: Responsible for: looking up or registering hotel guests by email. Traces to: technical: UC-1 needs a searchable guest collection.
- **RoomType**: Responsible for: enforcing the terms of a room category. Traces to: entity Room Type.
- **Room**: Responsible for: representing the service condition of one physical room. Traces to: entity Room.
- **ReservationStatus**: Responsible for: representing a reservation's permitted lifecycle states. Traces to: technical: UC-2 through UC-4 require explicit status validation.
- **Reservation**: Responsible for: preserving the terms of an individual booking. Traces to: entity Reservation.
- **ReservationManager**: Responsible for: maintaining the collection of reservations. Traces to: technical: availability checks require all active bookings.
- **RoomCatalog**: Responsible for: selecting rooms eligible for a requested stay. Traces to: technical: UC-1 searches the whole room inventory.
- **Stay**: Responsible for: recording the actual visit through checkout. Traces to: entity Stay.
- **Bill**: Responsible for: preserving an itemized checkout charge record. Traces to: entity Bill.
- **HotelService**: Responsible for: coordinating clerk-initiated hotel use cases. Traces to: technical: several use cases cross multiple domain objects.
- **HotelDemo**: Responsible for: running the assignment's twelve-step scenario. Traces to: technical: deliverable 6 requires an executable demonstration.

I used no hierarchy and no interface because Standard, Deluxe, and Suite differ only in capacity, rates, and minimum nights, which are stored in RoomType instances rather than subclasses.


## Deliverable 3: Sequence Diagrams

### UC-1: Reserve a Room

```mermaid
sequenceDiagram
    actor Clerk
    participant HotelService
    participant RoomCatalog
    participant Room
    participant RoomType
    participant ReservationManager
    participant GuestRegistry
    participant Reservation

    Clerk->>HotelService: findOffers(checkIn, checkOut, guests)
    HotelService->>RoomCatalog: findAvailableRooms(checkIn, checkOut, guests)
    loop For each physical room
        RoomCatalog->>Room: isInService()
        Room-->>RoomCatalog: inService
        RoomCatalog->>Room: getRoomType()
        Room-->>RoomCatalog: type
        RoomCatalog->>RoomType: canAccommodate(guests)
        RoomType-->>RoomCatalog: fitsGuests
        RoomCatalog->>RoomType: meetsMinimumStay(nights)
        RoomType-->>RoomCatalog: enoughNights
        RoomCatalog->>ReservationManager: isRoomAvailable(room, checkIn, checkOut)
        ReservationManager-->>RoomCatalog: available
    end
    RoomCatalog-->>HotelService: eligibleRooms
    alt No room fits (2a)
        HotelService-->>Clerk: exception - no room free for request
    else Eligible rooms exist
        loop For each eligible room and booked night
            HotelService->>Room: getRoomType()
            Room-->>HotelService: type
            HotelService->>RoomType: getRateForDate(night)
            RoomType-->>HotelService: currentNightRate
        end
        HotelService-->>Clerk: room numbers, categories, quoted totals
        Clerk->>HotelService: reserveRoom(roomNumber, checkIn, checkOut, guests, name, email, phone, madeOn)
        HotelService->>RoomCatalog: getRoom(roomNumber)
        RoomCatalog-->>HotelService: selectedRoom
        HotelService->>GuestRegistry: findOrCreateGuest(name, email, phone)
        GuestRegistry-->>HotelService: existingOrNewGuest
        HotelService->>Room: getRoomType()
        Room-->>HotelService: selectedType
        HotelService->>Room: isInService()
        Room-->>HotelService: inService
        HotelService->>RoomType: canAccommodate(guests)
        RoomType-->>HotelService: fitsGuests
        HotelService->>RoomType: meetsMinimumStay(nights)
        RoomType-->>HotelService: enoughNights
        HotelService->>ReservationManager: getFirstUnavailableNight(room, checkIn, checkOut)
        ReservationManager-->>HotelService: conflictingNightOrNull
        alt Room was taken meanwhile (6a)
            HotelService-->>Clerk: exception - room no longer free
            Clerk->>HotelService: findOffers(checkIn, checkOut, guests)
            HotelService->>RoomCatalog: findAvailableRooms(checkIn, checkOut, guests)
            RoomCatalog-->>HotelService: refreshedEligibleRooms
            HotelService-->>Clerk: refreshed offers (return to step 2)
        else Still free
            HotelService->>ReservationManager: nextConfirmationNumber()
            ReservationManager-->>HotelService: confirmationNumber
            HotelService->>Reservation: Reservation(confirmationNumber, guest, room, checkIn, checkOut, guests, madeOn)
            Reservation-->>HotelService: newReservation
            HotelService->>Reservation: recordBookedRates()
            Reservation->>Room: getRoomType()
            Room-->>Reservation: type
            loop Every night from checkIn through day before checkOut
                Reservation->>RoomType: getRateForDate(night)
                RoomType-->>Reservation: bookedRate
            end
            HotelService->>ReservationManager: addReservation(reservation)
            HotelService->>Reservation: calculateTotal()
            Reservation-->>HotelService: bookedTotal
            HotelService-->>Clerk: reservation with confirmation, room, dates, total
        end
    end
```

### UC-4: Check Out

```mermaid
sequenceDiagram
    actor Clerk
    participant HotelService
    participant ReservationManager
    participant Reservation
    participant Stay
    participant Bill
    participant RoomCatalog
    participant Room

    Clerk->>HotelService: checkOut(roomNumber, departureTime)
    HotelService->>ReservationManager: findActiveForRoom(roomNumber)
    alt Nobody is staying in the room (2a)
        ReservationManager-->>HotelService: exception - no stay in progress
        HotelService-->>Clerk: exception - no stay in progress
    else Active stay found
        ReservationManager-->>HotelService: activeReservation
        HotelService->>Reservation: getStay()
        Reservation-->>HotelService: stay
        HotelService->>Stay: endStay(departureTime)
        HotelService->>Stay: issueBill(billNumber, issuedOn)
        Stay->>Reservation: getBookedRates()
        Reservation-->>Stay: originalNightlyRates
        Stay->>Reservation: calculateTotal()
        Reservation-->>Stay: originalBookedTotal
        Stay->>Bill: Bill(billNumber, issuedOn, nightlyCharges, total)
        Bill-->>Stay: newBill
        Stay-->>HotelService: itemizedBill
        HotelService->>Reservation: complete()
        HotelService-->>Clerk: itemizedBill
        alt Room needs repair (7a)
            Clerk->>HotelService: reportRoomNeedsRepair(roomNumber)
            HotelService->>RoomCatalog: getRoom(roomNumber)
            RoomCatalog-->>HotelService: physicalRoom
            HotelService->>Room: setInService(false)
        else Room needs no repair
            Note over Clerk,HotelService: No service-status change required
        end
    end
```

## Deliverable 4: Responsibility Table

Job | Owner | Method | Data it needs, and the class that holds it | 
|---|---|---|---|
| 1. Find a guest by email, or record a new guest | GuestRegistry | findOrCreateGuest(name: String, email: String, phone: String): Guest | Email indexed collection: GuestRegistry; guest contact values: Guest / method inputs. |
| 2. Say whether a room is free for every night of a date range | ReservationManager | isRoomAvailable(room: Room, checkIn: LocalDate, checkOut: LocalDate): boolean | Existing reservations and their status/date/room: ReservationManager → Reservation; requested room and dates: parameters / Room. |
| 3. Find every room that is in service, holds the guests, meets the minimum nights, and is free | RoomCatalog | `findAvailableRooms(checkIn: LocalDate, checkOut: LocalDate, guests: int): List<Room>` | Physical room list: RoomCatalog; service status: Room; capacity/minimum: RoomType; conflicts: ReservationManager. |
| 4. Say what one night costs for a room type at today's rates | RoomType | getRateForDate(date: LocalDate): int | Current weekday/weekend rates: RoomType; calendar day: LocalDate parameter. |
| 5. Say whether a stay is long enough for a room type | RoomType | meetsMinimumStay(nights: int): boolean | Minimum nights: RoomType; requested number of nights: parameter.  |
| 6. Record the rate booked for each night of a new reservation | Reservation | recordBookedRates(): void | Dates and frozen rate map: Reservation; room: Room; type's current rates: RoomType. |
| 7. Find a reservation by confirmation number | ReservationManager | findByConfirmationNumber(confirmationNumber: String): Reservation | Stored reservation list: ReservationManager; confirmation number: Reservation. | 
| 8. Work out what a guest owes at checkout | Reservation | calculateTotal(): int | Per-night rates frozen at booking: Reservation; checkout coordination: HotelService → Stay. | 

## Deliverable 7: Reflection

At first I considered putting the complete room search in ReservationManager since it already needed booking history to detect conflicts. However, while mapping UC-1 I noticed that it would make the reservation collection also own all physical rooms and the rules for filtering them. I ended up moving job 3, findAvailableRooms() to RoomCatalog. That class now iterates the inventory, asks RoomType about capacity and minimum nights, and gives booking conflicts to ReservationManager.isRoomAvailable(). As a result the two collections are kept separate while each class performs the calculation for which it has the information. 

I'm less certain about letting Stay.issueBill() construct the Bill instead of having HotelService create it directly. Putting bill creation on Stay makes the link between a completed visit and its one final bill explicit but it requires Stay to reach back to Reservation for booked charges. Creating bills in HotelService would simplify Stay, but would concentrate more business details in the workflow coordinator. If Harborview later supports seperate invoices or partially paid stays that change would test whether the current Stay owned bill creation still makes sense. 