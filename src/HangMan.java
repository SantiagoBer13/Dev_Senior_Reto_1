import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangMan {
    static Scanner sc = new Scanner(System.in);
    static List<String> words = List.of(
            "JAVA", "PYTHON", "COMPILADOR", "INTERRUPTOR", "DEBUG",
            "VARIABLE", "CLASE", "OBJETO", "ALGORITMO", "FUNCION",
            "INTERFAZ", "HERENCIA", "POLIMORFISMO", "ENCAPSULACION",
            "BUCLE", "ARRAY", "EXCEPCION", "GITHUB", "FRAMEWORK",
            "SERVIDOR", "CLIENTE", "SQL", "JAVASCRIPT", "ANGULAR",
            "REACT", "VUE", "API", "LAMBDA", "DOCKER", "KUBERNETES",
            "PROGRAMACION", "METODO", "MODULO", "COMPONENTE", "SCRIPT",
            "PROTOCOLO", "REDES", "CONSOLA", "DEPURADOR", "OPERADOR",
            "ESTRUCTURA", "TIPO", "CONDICIONAL", "ASINCRONICO", "PROMESA",
            "COLA", "PILA", "HASHMAP", "STRING", "ENTERO", "BOOLEANO",
            "CICLO", "FUNCIONAL", "PROCEDURAL", "ORIENTADO", "SERVICIOS",
            "CLOUD", "MICROSERVICIOS", "TOKEN", "AUTENTICACION", "AUTORIZACION",
            "CIENCIA", "DATOS", "BIGDATA", "MACHINELEARNING", "ALGORITMIA",
            "REDNEURONAL", "INTELIGENCIA", "BLOCKCHAIN", "CRIPTOMONEDA",
            "CONTAINER", "VIRTUALIZACION", "HOSTING", "DEPLOY", "INTEGRACION",
            "CONTINUOUS", "MONOLITICO", "ESCALABILIDAD", "RENDIMIENTO", "CACHE",
            "ESTADISTICA", "RECURSIVIDAD", "OPTIMIZACION", "PRUEBAS", "AUTOMATIZACION",
            "CIBERSEGURIDAD", "FIREWALL", "ENCRIPTACION", "HASHING", "CERTIFICADO",
            "HTTPS", "TLS", "SSL", "BACKEND", "FRONTEND", "FULLSTACK",
            "NOSECUENCIAL", "INDICE", "CONSULTA", "TRIGGER", "TRANSACTION", "CONCURRENCIA");
    static String wordRandom;
    static String[] lettersWordRandom;
    static String[] lettersFound;
    static List<String> lettersTried;
    static Random rdm = new Random();
    static final String[] HANGMAN_STAGES = {
            """
                       +------+
                       |      |
                       |
                       |
                       |
                       |
                       |
                    +--+--+
                    |     |
                    """,
            """
                       +------+
                       |      |
                       |      O
                       |
                       |
                       |
                       |
                    +--+--+
                    |     |
                    """,
            """
                       +------+
                       |      |
                       |      O
                       |      |
                       |      |
                       |
                       |
                    +--+--+
                    |     |
                    """,
            """
                       +------+
                       |      |
                       |      O
                       |     \\|
                       |      |
                       |
                       |
                    +--+--+
                    |     |
                    """,
            """
                       +------+
                       |      |
                       |      O
                       |     \\|/
                       |      |
                       |
                       |
                    +--+--+
                    |     |
                    """,
            """
                       +------+
                       |      |
                       |      O
                       |     \\|/
                       |      |
                       |     /
                       |
                    +--+--+
                    |     |
                    """,
            """
                       +------+
                       |      |
                       |      O
                       |     \\|/
                       |      |
                       |     / \\
                       |
                    +--+--+
                    |     |
                    """
    };

    public static boolean game() {
        System.out.println("Ahorcado");
        chooseWordRandom();
        return playHangman();
    }

    public static void chooseWordRandom() {
        wordRandom = words.get(rdm.nextInt(words.size()));
        lettersWordRandom = wordRandom.split("");
        lettersFound = new String[lettersWordRandom.length];
        for (int i = 0; i < lettersWordRandom.length; i++) {
            lettersFound[i] = "_";
        }
        lettersTried = new ArrayList<>();
    }

    public static void showLines() {
        for (String letter : lettersFound) {
            System.out.print(letter + " ");
        }
        System.out.println();
    }

    public static boolean playHangman() {
        String letter;
        boolean salir, desicition = false;
        int failes = 0;
        do {
            System.out.printf("Palabra a adivinar: ");
            showLines();
            System.out.print("Letras intentadas: ");
            showLettersTried();
            System.out.printf("Intentos fallidos: %d.%n", failes);
            System.out.println(showProgress(failes));
            salir = false;
            System.out.print("Ingresa una letra o la palabra completa: ");
            letter = sc.nextLine().toUpperCase();

            if (letter.length() == 1 && !lettersTried.contains(letter)) {
                lettersTried.add(letter);
            }

            if (wordRandom.equals(letter)) {
                System.out.println("¡Felicidades, has ganado!");
                System.out.printf("La palabra es: %s%n", wordRandom);
                desicition = true;
                break;
            }

            if (wordRandom.contains(letter)) {
                System.out.printf("La letra %s está en la palabra.%n", letter);
                for (int i = 0; i < lettersWordRandom.length; i++) {
                    if (lettersWordRandom[i].equals(letter)) {
                        lettersFound[i] = letter;
                    }
                }
            } else {
                failes++;
            }

            for (String le : lettersFound) {
                if (le.equals("_")) {
                    salir = true;
                    break;
                }
            }

            if (!salir) {
                System.out.println("\n¡Felicidades, has ganado!");
                System.out.printf("La palabra es: %s%n", wordRandom);
                System.out.println(showProgress(failes));
                desicition = true;
                break;
            }

            if (failes == 6) {
                System.out.println("\n¡Has perdido!");
                System.out.printf("La palabra era: %s%n", wordRandom);
                System.out.println(showProgress(failes));
                desicition = false;
                break;
            }
        } while (!wordRandom.equals(letter) && salir);
        return desicition;
    }

    public static String showProgress(int failes) {
        return HANGMAN_STAGES[failes];
    }

    public static void showLettersTried() {
        for (String letter : lettersTried) {
            System.out.print(letter + " ");
        }
        System.out.println();
    }
}
