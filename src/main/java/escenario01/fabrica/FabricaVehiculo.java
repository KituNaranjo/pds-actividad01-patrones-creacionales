package escenario01.fabrica;

import escenario01.producto.DatosVehiculo;
import escenario01.producto.Vehiculo;

// Creator (Factory Method): declara el método fábrica crearVehiculo()
// y contiene la lógica común que usa el producto sin conocer su clase concreta.

public abstract class FabricaVehiculo {

    // Factory Method: cada subclase decide qué Vehiculo concreto instanciar 
    protected abstract Vehiculo crearVehiculo(DatosVehiculo datos);

    // Operación del Creator: depende solo de la abstracción Vehiculo
        /** Operación del Creator: depende solo de la abstracción Vehiculo. */
    public Vehiculo registrar(DatosVehiculo datos) {
        Vehiculo vehiculo = crearVehiculo(datos);
        System.out.printf("""
                Registrado:
                    %s: [%s] %s %s %d
                    Avalúo: $%s
                    Costo de Matrícula: $%s

                """,
                vehiculo.getTipo(), vehiculo.getPlaca(), vehiculo.getMarca(),
                vehiculo.getModelo(), vehiculo.getAnioFabricacion(),
                vehiculo.getAvaluo(), vehiculo.calcularCostoMatricula());
        return vehiculo;
    }
}