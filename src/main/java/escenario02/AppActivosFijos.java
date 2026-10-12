package escenario02;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// Client del patrón Builder
public class AppActivosFijos {

    public static void main(String[] args) {
        System.out.println("=== Escenario 02 - Builder: registro de activos fijos ===");
        System.out.println();

        ActivoFijo computador = ActivoFijo.builder("AF-001", "Computador portátil", new BigDecimal("1200.00"))
                .conMarca("Dell")
                .conModelo("Latitude 5440")
                .conNumeroSerie("SN-7X91K2")
                .conCaracteristica("Procesador", "Intel Core i7-1355U")
                .conCaracteristica("Memoria RAM", "16 GB")
                .conUbicacion("Cuenca - Edificio Administrativo")
                .conCustodio("Ana Torres")
                .conFechaAdquisicion(LocalDate.of(2025, 3, 15))
                .conGarantiaMeses(36)
                .conProveedor("Tecnología Andina S.A.")
                .build();

        ActivoFijo mesa = ActivoFijo.builder("AF-002", "Mesa de reuniones", new BigDecimal("450.00"))
                .conCaracteristica("Material", "Madera MDF")
                .conCaracteristica("Dimensiones", "240 x 120 x 75 cm")
                .conUbicacion("Sala de reuniones 2")
                .conCustodio("Luis Pérez")
                .build();

        ActivoFijo impresora = ActivoFijo.builder("AF-003", "Impresora multifunción", new BigDecimal("380.00"))
                .conMarca("Epson")
                .conModelo("EcoTank L6270")
                .conObservaciones("Asignada a secretaría general")
                .build();

        DirectorActivos director = new DirectorActivos();
        ActivoFijo portatil = director.computadorPortatil("AF-004", new BigDecimal("980.00"),
                "Lenovo", "ThinkPad E14", "SN-LN4421", "AMD Ryzen 5 7530U", "María Gómez");
        ActivoFijo silla = director.mueble("AF-005", "Silla ergonómica", new BigDecimal("150.00"),
                "Malla y aluminio", "65 x 65 x 120 cm", "Oficina 105", "Carlos Ruiz");

        for (ActivoFijo activo : List.of(computador, mesa, impresora, portatil, silla)) {
            System.out.println(activo);
            System.out.println();
        }

        try {
            ActivoFijo.builder("AF-006", "Monitor", new BigDecimal("0")).build();
        } catch (IllegalStateException e) {
            System.out.println("Validación: " + e.getMessage());
        }
        try {
            ActivoFijo.builder(" ", "Monitor", new BigDecimal("200")).build();
        } catch (IllegalArgumentException e) {
            System.out.println("Validación: " + e.getMessage());
        }
    }
}