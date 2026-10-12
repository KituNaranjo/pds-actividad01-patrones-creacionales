package escenario01.fabrica;

import escenario01.producto.Auto;
import escenario01.producto.DatosVehiculo;
import escenario01.producto.Vehiculo;

// ConcreteCreator para Auto

public class FabricaAuto extends FabricaVehiculo {

    @Override
    protected Vehiculo crearVehiculo(DatosVehiculo d) {
        return new Auto(d.placa(), d.marca(), d.modelo(), d.anioFabricacion(), d.avaluo());
    }
}