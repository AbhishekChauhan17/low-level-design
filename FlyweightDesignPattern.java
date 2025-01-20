// Question: Car Manufacturing System

// You are tasked with designing a Car Manufacturing System using the Flyweight Design Pattern. The system should optimize memory usage by reusing car models that share common properties (intrinsic state) and customizing only the unique features (extrinsic state).
// Requirements

//     Car Properties:
//         Intrinsic State (shared):
//             Model Name (e.g., "Sedan", "SUV").
//             Manufacturer (e.g., "Toyota", "Honda").
//         Extrinsic State (unique):
//             Color (e.g., "Red", "Blue").
//             License Plate (e.g., "ABC-123").
//             Owner (e.g., "John Doe").

//     Car Class:
//         Implement a Car class with shared and unique properties.
//         Include a display method to show both intrinsic and extrinsic details.

//     Car Factory:
//         Use a CarFactory to manage and reuse car objects with the same intrinsic state.

//     Client Code:
//         Simulate creating multiple cars with different unique details but shared intrinsic properties.

import java.util.HashMap;
import java.util.Map;

enum Color {
  BLACK,
  BLUE,
  GREY
}

class Car {
  String model;
  String manufacturer;
  public Car(String model, String manufacturer) {
    this.model = model;
    this.manufacturer = manufacturer;
  }
  public void createVehicle(Color color, String licensePlate, String ownerName) {
    System.out.println("[MODEL] " + this.model + " [MANUFACTURER] " + this.manufacturer);
    System.out.println("Creating vehicle with [COLOR] " + color + " [LICENSE PLATE NUMBER] " + licensePlate + " [OWNER NAME] " + ownerName);
  }
}

class CarFactory {
  Map<String, Car> carDB = new HashMap<>();
  public Car getCar(String model, String manufacturer) {
    String keyString = model + "$$$" + manufacturer;
    if (carDB.containsKey(keyString)) {
      return carDB.get(keyString);
    }
    else {
      Car car = new Car(model, manufacturer);
      carDB.put(keyString, car);
      return car;
    }
  }
}

public class FlyweightDesignPattern {
  public static void main(String []args) {
    CarFactory carFactory = new CarFactory();
    Car car = carFactory.getCar("sedan", "toyota");
    car.createVehicle(Color.BLACK, "ABC9876", "abhishek");

    Car car2 = carFactory.getCar("sedan", "toyota");
    car2.createVehicle(Color.BLACK, "ABC9876", "abhishek");

    Car car3 = carFactory.getCar("SUV", "toyota");
    car3.createVehicle(Color.BLACK, "ABC9876", "abhishek");

    Car car4 = carFactory.getCar("sedan", "toyota");
    car4.createVehicle(Color.BLACK, "ABC9876", "abhishek");
  }
}
