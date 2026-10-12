package escenario01.fabrica;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// Selecciona en tiempo de ejecución el ConcreteCreator según el tipo
// indicado por el cliente. Agregar un tipo = crear su fábrica y registrarla;
// no se modifica la lógica que usa los vehículos ni se usa un switch

public class RegistroFabricas {

    private final Map<String, FabricaVehiculo> fabricas = new HashMap<>();

    public RegistroFabricas registrar(String tipo, FabricaVehiculo fabrica) {
        fabricas.put(normalizar(tipo), fabrica);
        return this;
    }

    public FabricaVehiculo obtener(String tipo) {
        FabricaVehiculo fabrica = fabricas.get(normalizar(tipo));
        if (fabrica == null)
            throw new IllegalArgumentException("Tipo de vehículo no soportado: " + tipo
                    + ". Disponibles: " + tiposDisponibles());
        return fabrica;
    }

    public Set<String> tiposDisponibles() {
        return new TreeSet<>(fabricas.keySet());
    }

    private static String normalizar(String tipo) {
        return (tipo == null) ? "" : tipo.trim().toUpperCase(Locale.ROOT);
    }

    /** Configuración por defecto: ÚNICO lugar que conoce las fábricas concretas. */
    public static RegistroFabricas porDefecto() {
        return new RegistroFabricas()
                .registrar("AUTO", new FabricaAuto())
                .registrar("CAMIONETA", new FabricaCamioneta())
                .registrar("CAMION", new FabricaCamion());
    }
}