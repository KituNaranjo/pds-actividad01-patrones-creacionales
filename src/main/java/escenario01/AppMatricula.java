package escenario01;

import escenario01.fabrica.FabricaVehiculo;
import escenario01.fabrica.RegistroFabricas;
import escenario01.producto.DatosVehiculo;
import escenario01.producto.Vehiculo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

// Client: No depende de Auto, Camioneta ni Camion

public class AppMatricula {

    public static void main(String[] args) {
        RegistroFabricas registro = RegistroFabricas.porDefecto();

        // Simula la información que llega del cliente en tiempo de ejecución
        List<DatosVehiculo> solicitudes = List.of(
                new DatosVehiculo("Auto", "PBA-1234", "Chevrolet", "Aveo", 2018,
                        new BigDecimal("12000"), Map.of()),
                new DatosVehiculo("Camioneta", "PCD-5678", "Toyota", "Hilux", 2022,
                        new BigDecimal("35000"),
                        Map.of("capacidadCargaKg", "1000", "usoComercial", "true")),
                new DatosVehiculo("Camion", "PEF-9012", "Hino", "Serie 500", 2015,
                        new BigDecimal("60000"),
                        Map.of("tonelaje", "8.5", "numeroEjes", "2")));

        System.out.println("=== Escenario 01 - Factory Method: matrícula vehicular ===");
        BigDecimal totalRecaudado = BigDecimal.ZERO;

        for (DatosVehiculo datos : solicitudes) {
            FabricaVehiculo fabrica = registro.obtener(datos.tipo());   // elige según el dato
            Vehiculo vehiculo = fabrica.registrar(datos);               // trato polimórfico
            totalRecaudado = totalRecaudado.add(vehiculo.calcularCostoMatricula());
        }
        System.out.println("Total a recaudar: $" + totalRecaudado);

        // Caso de error: Tipo no registrado
        try {
            registro.obtener("Bus");
        } catch (IllegalArgumentException e) {
            System.out.println("Validación: " + e.getMessage());
        }
    }
}