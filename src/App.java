import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class App {

  static Scanner sc = new Scanner(System.in);

  // Nombres de los planetas
  static String[] planets = {
      "Mercurio", "Venus", "Tierra", "Marte", "Jupiter", "Saturno", "Urano", "Neptuno"
  };

  // Distancias desde la Tierra en millones de kilómetros
  static double[] distances = {
      91.7, 41.4, 0.0, 78.3, 628.7, 1277.4, 2721.8, 4353.0
  };

  static LinkedHashMap<String, Double> distancePlanets = new LinkedHashMap<>();

  // Nombres de las naves espaciales
  static String[] ships = {
      "Voyager-1", "Enterprise-X", "Solar Wind",
      "Starlight Cruiser", "Lunar Explorer", "Nebula Rider"
  };

  // Velocidades promedio de las naves en kilómetros por segundo (km/s)
  static double[] speeds = {
      17.0, 25.4, 15.6,
      20.3, 12.5, 30.8
  };

  static Map<String, Double> shipSpeeds = Map.of(
      "Voyager-1", 17.0,
      "Enterprise-X", 25.4,
      "Solar Wind", 15.6,
      "Starlight Cruiser", 20.3,
      "Lunar Explorer", 12.5,
      "Nebula Rider", 30.8);

  static LinkedHashMap<String, Integer> shipPassagersCount = new LinkedHashMap<>();

  // User
  static String shipSelected, planetSelected;
  static Double distancePlanetSelected, timeEstimated;
  static int passagers;

  public static void main(String[] args) throws Exception {

    distancePlanets.put("Mercurio", 91.7);
    distancePlanets.put("Venus", 41.4);
    distancePlanets.put("Tierra", 0.0);
    distancePlanets.put("Marte", 78.3);
    distancePlanets.put("Jupiter", 628.7);
    distancePlanets.put("Saturno", 1277.4);
    distancePlanets.put("Urano", 2721.8);
    distancePlanets.put("Neptuno", 4353.0);
    shipPassagersCount.put("Voyager-1", 150);
    shipPassagersCount.put("Enterprise-X", 80);
    shipPassagersCount.put("Solar Wind", 200);
    shipPassagersCount.put("Starlight Cruiser", 120);
    shipPassagersCount.put("Lunar Explorer", 300);
    shipPassagersCount.put("Nebula Rider", 50);

    
  }

  public static void showMenu() {
    System.out.println("==============================");
    System.out.println("      MENU PRINCIPAL");
    System.out.println("==============================");
    System.out.println("1. Seleccionar un planeta de destino.");
    System.out.println("2. Seleccionar una nave espacial.");
    System.out.println("3. Iniciar simulacion de viaje.");
    System.out.println("4. Salir.");
  }

  public static void selectPlanet() {
    System.out.println("\n========== SELECCION DE UN PLANETA ==========");
    int i = 0;
    for (Map.Entry<String, Double> planet : distancePlanets.entrySet()) {
      System.out.printf("%d) Planeta: %-10s | Distancia: %8.2f millones de kilometros.%n", i + 1, planet.getKey(),
          planet.getValue());
      i++;
    }
    System.out.print("Selecciona un planeta: ");
    int selection = Integer.parseInt(sc.nextLine());
    if (selection > 0 && selection <= planets.length) {
      System.out.printf("\nHas seleccionado el planeta: %s.%n", planets[selection - 1]);
      planetSelected = planets[selection - 1];
    } else {
      System.out.println("\n[Error] Seleccion invalida. Intenta de nuevo.");
    }
  }

  public static void selectShip() {
    System.out.println("\n========== SELECCION DE UNA NAVE ==========");
    for (int i = 0; i < ships.length; i++) {
      System.out.printf("%d) Nave: %s%n", i + 1, ships[i]);
    }
    System.out.print("Selecciona una nave: ");
    int selection = Integer.parseInt(sc.nextLine());
    if (selection > 0 && selection <= ships.length) {
      shipSelected = ships[selection - 1];
      System.out.printf("\nHas seleccionado la nave: %s.%n", shipSelected);
      int maxPassagers;
      do {
        maxPassagers = shipPassagersCount.get(shipSelected);
        System.out.printf("Esta nave tiene capacidad maxima de %d pasajeros.%n", maxPassagers);
        System.out.print("Ingresa la cantidad de pasajeros: ");
        passagers = Integer.parseInt(sc.nextLine());
        if (passagers < 0 || passagers > maxPassagers) {
          System.out.println("\n[Error] Ingresa un numero valido de pasajeros.");
        }
      } while (passagers < 0 || passagers > maxPassagers);
    } else {
      System.out.println("\n[Error] Seleccion invalida. Intenta de nuevo.");
    }
  }

  public static void calculateDistance() {
    distancePlanetSelected = distancePlanets.get(planetSelected);
    System.out.printf("\nLa distancia de la Tierra a %s es de %.2f millones de kilometros.%n", planetSelected,
        distancePlanetSelected);
  }

  public static void calculateTimeTrip() {
    timeEstimated = (distancePlanetSelected * 1000000 / shipSpeeds.get(shipSelected)) / 3600 / 24;
    System.out.printf("Con la nave %s se demora aproximadamente %.2f días.%n", shipSelected, timeEstimated);
  }

  public static void calculateFuel() {
    Double time = (distancePlanetSelected * 1000000 / shipSpeeds.get(shipSelected)) / 3600;
    Double fuelConsumption = time * passagers * 0.5; // 0.5 litros por hora por pasajero
    System.out.printf("Se consumiran %.2f litros de combustible en el viaje.%n", fuelConsumption);
  }
}
