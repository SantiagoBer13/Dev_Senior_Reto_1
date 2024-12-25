# Simulador de Viaje Interestelar

Este proyecto es un simulador que permite calcular y simular viajes a diferentes planetas utilizando naves espaciales, considerando distancias y velocidades específicas.

## Características

- **Selección de planetas**: El usuario puede elegir entre varios planetas y conocer su distancia desde la Tierra.
- **Selección de naves**: Diferentes naves espaciales con velocidades específicas.
- **Cálculo del tiempo de viaje**: Estimación del tiempo necesario para llegar al destino en días.
- **Simulación del viaje**: Progreso dinámico con eventos aleatorios que afectan el curso del viaje.

## Tecnologías Utilizadas

- **Lenguaje**: Java
- **IDE recomendado**: VSCODE, IntelliJ IDEA o Eclipse
- **Manejo de eventos aleatorios**: Utiliza la clase `Random` de Java

## Estructura del Proyecto

- **Main Class**: Contiene el flujo principal del programa.
- **Métodos principales**:
  - `calculateTimeTrip()`: Calcula el tiempo estimado del viaje en días.
  - `simulateTrip()`: Simula el progreso del viaje, mostrando actualizaciones y eventos inesperados.
  - `HangMan.game()`: Subjuego que permite resolver problemas durante el viaje.

## Requisitos del Sistema

- **JDK**: Java 8 o superior.

## Configuración Inicial

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu-usuario/simulador-viaje-interestelar.git
   ```

2. Abre el proyecto en tu IDE preferido.
3. Compila el proyecto:
   ```bash
   javac Main.java
   ```
4. Ejecuta el programa:
   ```bash
   java Main
   ```

## Uso

1. Selecciona un planeta de la lista disponible.
2. Selecciona una nave espacial.
3. El programa calculará el tiempo estimado en días.
4. Inicia la simulación para visualizar el progreso del viaje y manejar eventos inesperados.

## Ejemplo de Salida

```
========== INICIANDO VIAJE ==========
Distancia inicial al destino: 78.34 millones de kilómetros.
Tiempo estimado: 13.02 días.
Progreso del viaje: 45.0% completado.
Tiempo estimado restante: 7.15 días.
El viaje puede presentar problemas, soluciona el problema con el juego.
¡Llegada exitosa a Marte!
```

## Autor

**Santiago Bernal Tinjacá**  
Desarrollador apasionado por la exploración del espacio y la tecnología.

---
¡Explora los confines del universo con este simulador! 🌌🚀
Proxima Actualización.