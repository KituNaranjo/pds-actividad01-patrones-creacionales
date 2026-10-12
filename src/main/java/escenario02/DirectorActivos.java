package escenario02;

import java.math.BigDecimal;
import java.time.LocalDate;

// Director (Builder): encapsula configuraciones frecuentes para que el
// proceso de creación sea reutilizable y consistente. Es opcional:
// el cliente también puede usar el Builder directamente

public class DirectorActivos {

    // Todo portátil nuevo lleva serie, procesador, custodio y 36 meses de garantía
    public ActivoFijo computadorPortatil(String codigo, BigDecimal precio, String marca, String modelo,
                                         String serie, String procesador, String custodio) {
        return ActivoFijo.builder(codigo, "Computador portátil", precio)
                .conMarca(marca)
                .conModelo(modelo)
                .conNumeroSerie(serie)
                .conCaracteristica("Procesador", procesador)
                .conCustodio(custodio)
                .conFechaAdquisicion(LocalDate.now())
                .conGarantiaMeses(36)
                .build();
    }

    // Un mueble solo necesita material, dimensiones, ubicación y custodio
    public ActivoFijo mueble(String codigo, String descripcion, BigDecimal precio, String material,
                             String dimensiones, String ubicacion, String custodio) {
        return ActivoFijo.builder(codigo, descripcion, precio)
                .conCaracteristica("Material", material)
                .conCaracteristica("Dimensiones", dimensiones)
                .conUbicacion(ubicacion)
                .conCustodio(custodio)
                .build();
    }
}