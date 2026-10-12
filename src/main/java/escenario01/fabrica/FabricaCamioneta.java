package escenario01.fabrica;

import escenario01.producto.Camioneta;
import escenario01.producto.DatosVehiculo;
import escenario01.producto.Vehiculo;

// ConcreteCreator para Camioneta

public class FabricaCamioneta extends FabricaVehiculo {

    @Override
    protected Vehiculo crearVehiculo(DatosVehiculo d) {
        return new Camioneta(d.placa(), d.marca(), d.modelo(), d.anioFabricacion(), d.avaluo(), 
        Integer.parseInt(d.requerido("capacidadCargaKg")), 
        Boolean.parseBoolean(d.requerido("usoComercial")));
    }
}