public class CarAgency {
    public static void main(String[] args) {
        // Creating objects of different vehicle types
        Vehicle car = new Car("Toyota", "Camry", 2021, 4, "Petrol");
        Vehicle motorcycle = new Motorcycle("Harley Davidson", "Street 750", 2020, 2, "Cruiser");
        Vehicle truck = new Truck("Ford", "F-150", 2019, 2.5, "Automatic");

        // Displaying details of each vehicle
        displayVehicleDetails(car);
        displayVehicleDetails(motorcycle);
        displayVehicleDetails(truck);
    }

    // Method to display vehicle details
    private static void displayVehicleDetails(Vehicle vehicle) {
        System.out.println("Make: " + vehicle.getMake());
        System.out.println("Model: " + vehicle.getModel());
        System.out.println("Year: " + vehicle.getYear());
        if (vehicle instanceof CarVehicle) {
            CarVehicle car = (CarVehicle) vehicle;
            System.out.println("Number of Doors: " + car.getNumberOfDoors());
            System.out.println("Fuel Type: " + car.getFuelType());
        }
        if (vehicle instanceof MotorVehicle) {
            MotorVehicle motorcycle = (MotorVehicle) vehicle;
            System.out.println("Number of Wheels: " + motorcycle.getNumberOfWheels());
            System.out.println("Motorcycle Type: " + motorcycle.getMotorcycleType());
        }
        if (vehicle instanceof TruckVehicle) {
            TruckVehicle truck = (TruckVehicle) vehicle;
            System.out.println("Cargo Capacity: " + truck.getCargoCapacity() + " tons");
            System.out.println("Transmission Type: " + truck.getTransmissionType());
        }
        System.out.println();
    }
}


interface Vehicle {
    String getMake();
    String getModel();
    int getYear();
}

interface CarVehicle {
    int getNumberOfDoors();
    String getFuelType();
}

interface MotorVehicle {
    int getNumberOfWheels();
    String getMotorcycleType();
}

interface TruckVehicle {
    double getCargoCapacity();
    String getTransmissionType();
}

class Car implements Vehicle, CarVehicle {
    private String make;
    private String model;
    private int year;
    private int numberOfDoors;
    private String fuelType;

    public Car(String make, String model, int year, int numberOfDoors, String fuelType) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    @Override
    public String getFuelType() {
        return fuelType;
    }
}

class Motorcycle implements Vehicle, MotorVehicle {
    private String make;
    private String model;
    private int year;
    private int numberOfWheels;
    private String motorcycleType;

    public Motorcycle(String make, String model, int year, int numberOfWheels, String motorcycleType) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.numberOfWheels = numberOfWheels;
        this.motorcycleType = motorcycleType;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    @Override
    public String getMotorcycleType() {
        return motorcycleType;
    }
}

class Truck implements Vehicle, TruckVehicle {
    private String make;
    private String model;
    private int year;
    private double cargoCapacity;
    private String transmissionType;

    public Truck(String make, String model, int year, double cargoCapacity, String transmissionType) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.cargoCapacity = cargoCapacity;
        this.transmissionType = transmissionType;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public String getTransmissionType() {
        return transmissionType;
    }
}

