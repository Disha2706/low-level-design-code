import java.util.List;

class ParkingLot {
    List<ParkingFloor> floors;
    List<Entrance> entrances;
    List<Exit> exits;

    Address address;

    String parkingLotName;

    bool isFull(Vehicle vehicle);

}

class ParkingFloor {
    int floorId;
    bool isFull;
    List<parkingSpace> parkingSpaces;
    DisplayBoard floorDisplayBoard;
}

class Gate {
    int gateId;
    ParkingAttendant parkingLotName;
}

class Entrance extends Gate {
    ParkingTicket getParkingTicket(Vehicle vehicle);
}

class Exit extends Gate {
    int costPerhour(Vehicle vehicle);

    long processParkingTicket(ParkingTicket ticket);
}

class Address {
    String streetname;
    String city,
    String state;
    String country;
    String pinCode;
}

class ParkingSpace {

    int spaceId;
    bool isFull;
    Vehicle vehicle;
    ParkingSpaceType parkingSpaceType;

}

enum ParkingSpaceType{
    FOUR_WHEELER;
    TWO_WHEELER;
}

class DisplayBoard {
    Map<ParkingSpaceType, Integer> countOfFreeSpaces;

    void reduceFreeSpot(ParkingSpaceType type);

    void increaseFreeSpot(ParkingSpaceType type);
}

class ParkingAttendant {
    void processVehicleEntry();

    void processPayment();
}

class Vehicle {
    String licenseNumber;
    ParkingSpaceType type;
    ParkingTicket parkingTicket;
}

class ParkingTicket {
    Intger ticketId;
    Integer floorId;
    ParkingSpaceType type;
    Date entryTime;
    Date exitTime;
    Integer price;

    void updateCost();

    void updateVehicleExitTime();
}