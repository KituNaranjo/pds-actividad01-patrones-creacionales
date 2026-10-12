package escenario01.fabrica;

import escenario01.producto.Camion;
import escenario01.producto.DatosVehiculo;
import escenario01.producto.Vehiculo;

// ConcreteCreator para Camion

public class FabricaCamion extends FabricaVehiculo {

    @Override
    protected Vehiculo crearVehiculo(DatosVehiculo d) {
        return new Camion(d.placa(), d.marca(), d.modelo(), d.anioFabricacion(), d.avaluo(),
                Double.parseDouble(d.requerido("tonelaje")),
                Integer.parseInt(d.requerido("numeroEjes")));
    }
}