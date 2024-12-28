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

  // Tasa de consumo de combustible por hora para cada nave en litros
  static Map<String, Double> fuelRates = Map.of(
      "Voyager-1", 200.0,
      "Enterprise-X", 300.0,
      "Solar Wind", 250.0,
      "Starlight Cruiser", 270.0,
      "Lunar Explorer", 320.0,
      "Nebula Rider", 150.0);

  static LinkedHashMap<String, Integer> shipPassagersCount = new LinkedHashMap<>();

  // User
  static String shipSelected, planetSelected;
  static Double distancePlanetSelected, timeEstimated, fuelSelected;
  static int passagers;

  // Atributo estático para el consumo de combustible
  static Double fuelConsumption;

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

    int option;
    do {
      showMenu();
      System.out.print("\nIngresa una opcion: ");
      option = Integer.parseInt(sc.nextLine());
      switch (option) {
        case 1:
          selectPlanet();
          break;
        case 2:
          selectShip();
          break;
        case 3:
          if (planetSelected == null || shipSelected == null) {
            System.out.println("\n[Error] Por favor selecciona un planeta y una nave antes de iniciar la simulacion.");
          } else {

            simulateTrip();
          }
          break;
        case 4:
          System.out.println("\nSaliendo del sistema. Adios!");
          break;
        default:
          System.out.println("\n[Error] Opcion invalida. Intenta de nuevo.");
          break;
      }
    } while (option != 4);
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
    int selection;
    do {
      System.out.print("Selecciona un planeta: ");
      selection = Integer.parseInt(sc.nextLine());
      if (selection > 0 && selection <= planets.length) {
        System.out.printf("\nHas seleccionado el planeta: %s.%n", planets[selection - 1]);
        planetSelected = planets[selection - 1];
      } else {
        System.out.println("\n[Error] Seleccion invalida. Intenta de nuevo.");
      }
    } while (selection < 1 || selection > planets.length);
  }

  public static void selectShip() {
    System.out.println("\n---------- SELECCIÓN DE NAVE ----------");
    for (int i = 0; i < ships.length; i++) {
        System.out.printf("%d) Nave: %s | Velocidad: %.1f km/s%n", i + 1, ships[i], shipSpeeds.get(ships[i]));
    }
    System.out.print("Selecciona una nave (1-" + ships.length + "): ");
    int selection = Integer.parseInt(sc.nextLine());
    if (selection > 0 && selection <= ships.length) {
        shipSelected = ships[selection - 1];
        System.out.printf("\nHas seleccionado la nave: %s%n", shipSelected);
        int maxPassengers = shipPassagersCount.get(shipSelected);
        do {
            System.out.printf("La capacidad máxima de esta nave es de %d pasajeros.%n", maxPassengers);
            System.out.print("Ingresa la cantidad de pasajeros: ");
            passagers = Integer.parseInt(sc.nextLine());
            if (passagers < 0 || passagers > maxPassengers) {
                System.out.println("\n[Error] Ingresa un número válido de pasajeros.");
            }
        } while (passagers < 0 || passagers > maxPassengers);
        calculateDistance();
        calculateTimeTrip();
        calculateFuel();
        calculateOxigen();
        do {
            System.out.printf("Se necesitan %.2f litros de combustible para este viaje.%n", fuelConsumption);
            System.out.print("Ingresa la cantidad de combustible que deseas llevar: ");
            fuelSelected = Double.parseDouble(sc.nextLine());
            if (fuelSelected < 0) {
                System.out.println("\n[Error] Ingresa una cantidad positiva de combustible.");
            }
        } while (fuelSelected < 0);
    } else {
        System.out.println("\n[Error] Selección inválida. Intenta de nuevo.");
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

  // Método corregido para calcular el combustible
  public static void calculateFuel() {

    // Tiempo total del viaje en horas
    Double time = (distancePlanetSelected * 1000000 / shipSpeeds.get(shipSelected)) / 3600;

    // Consumo total de combustible según la nave seleccionada
    fuelConsumption = time * fuelRates.get(shipSelected);
  }

  public static void calculateOxigen() {
    Double time = (distancePlanetSelected * 1000000 / shipSpeeds.get(shipSelected)) / 3600;
    Double oxygenConsumption = time * passagers * 1.0; // 1 kg de oxigeno por hora por pasajero
    System.out.printf("Se consumiran %.2f kg de oxigeno en el viaje.%n", oxygenConsumption);
  }

  public static void simulateTrip() {
    Random rm = new Random();
    System.out.println("\n========== INICIANDO VIAJE ==========");
    System.out.printf("Distancia inicial al destino: %.2f millones de kilómetros.%n", distancePlanetSelected);
    System.out.printf("Tiempo estimado: %.2f días.%n", timeEstimated);

    double remainingDistance = distancePlanetSelected; // Distancia restante en millones de km

    while (remainingDistance > 0) {
      // Consumo de combustible por millón de km
      double fuelWasted = ((1 * 1000000 / shipSpeeds.get(shipSelected)) / 3600) * fuelRates.get(shipSelected);
      if (fuelWasted > fuelSelected && fuelSelected > 0) {
        fuelSelected = 0.0;
      } else {
        fuelSelected -= fuelWasted;
      }

      // Recalcular tiempo estimado restante
      timeEstimated = (remainingDistance * 1000000 / shipSpeeds.get(shipSelected)) / 3600 / 24;

      // Progreso aleatorio
      if (rm.nextInt(10) == 1) {
        double percentage = ((distancePlanetSelected - remainingDistance) / distancePlanetSelected) * 100;
        System.out.printf("Progreso del viaje: %.2f%% completado.%n", percentage);
        System.out.printf("Tiempo estimado restante: %.2f días.%n", timeEstimated);
      }

      // Posibles problemas en el viaje

      if (rm.nextInt(50) == 1) {
        System.out.println("\nProblema detectado durante el viaje. Resolviendo...");
        boolean resolved = HangMan.game();
        if (resolved) {
            System.out.println("Problema resuelto con éxito. Continuando el viaje.");
        } else {
            remainingDistance += 5;
            System.out.printf("\nEl viaje se ha desviado. Nueva distancia al destino: %.2f millones de kilómetros%n", remainingDistance);
        }
    }

      remainingDistance -= 1; // Avanza 1 millón de km
      // Verificar combustible
      if (fuelSelected <= 0) {
        System.out.println("\nSe ha acabado el combustible. El viaje no puede continuar.");
        System.out.println("Fin del viaje. Llegada no exitosa.");
        return;
    }

    }

    // Viaje exitoso
    System.out.printf("\n¡Llegada exitosa al planeta %s!%n", planetSelected);
  }
}
