package escenario01.producto;

import java.math.BigDecimal;
import java.util.Map;

// Información que proporciona el cliente en tiempo de ejecución.
// Los datos comunes son campos; los específicos de cada tipo viajan
// en el mapa "adicionales" y cada fábrica sabe cuáles necesita.

public record DatosVehiculo(String tipo, String placa, String marca, String modelo,
                            int anioFabricacion, BigDecimal avaluo,
                            Map<String, String> adicionales) {

    // Constructor compacto: Se ejecuta al crear el record
    public DatosVehiculo {
        adicionales = (adicionales == null) ? Map.of() : Map.copyOf(adicionales);
    }

    // Devuelve un dato adicional obligatorio, o lanza error si falta
    public String requerido(String clave) {
        String valor = adicionales.get(clave);
        if (valor == null || valor.isBlank())
            throw new IllegalArgumentException("Falta el dato '" + clave + "' para el tipo " + tipo);
        return valor;
    }
}