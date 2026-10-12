package escenario01.producto;

import java.math.BigDecimal;

// ConcreteProduct: Además del avalúo incorpora capacidad y uso.
// Regla: $40 + 1.8% del avalúo + $0.02 por kg de carga,
// con recargo del 15% si es de uso comercial.

public class Camioneta extends Vehiculo {

    // 1) Constantes de la regla de negocio
    private static final BigDecimal TARIFA_BASE = new BigDecimal("40.00");
    private static final BigDecimal TASA_AVALUO = new BigDecimal("0.018");
    private static final BigDecimal VALOR_POR_KG = new BigDecimal("0.02");
    private static final BigDecimal RECARGO_COMERCIAL = new BigDecimal("1.15");

    // 2) Atributos propios de la camioneta
    private final int capacidadCargaKg;
    private final boolean usoComercial;

    // 3) Constructor: datos comunes + datos propios
    public Camioneta(String placa, String marca, String modelo, int anio, BigDecimal avaluo,
                     int capacidadCargaKg, boolean usoComercial) {
        super(placa, marca, modelo, anio, avaluo);
        if (capacidadCargaKg < 0)
            throw new IllegalArgumentException("Capacidad de carga inválida");
        this.capacidadCargaKg = capacidadCargaKg;
        this.usoComercial = usoComercial;
    }

    // 4) Cumplir el contrato
    @Override
    public BigDecimal calcularCostoMatricula() {
        BigDecimal total = TARIFA_BASE
                .add(getAvaluo().multiply(TASA_AVALUO))
                .add(VALOR_POR_KG.multiply(BigDecimal.valueOf(capacidadCargaKg)));

        if (isUsoComercial()) total = total.multiply(RECARGO_COMERCIAL);

        return redondear(total);
    }

    @Override
    public String getTipo() { 
        return "Camioneta"; 
    }

    // 5) Getters de los atributos propios
    public int getCapacidadCargaKg() { 
        return capacidadCargaKg; 
    }

    public boolean isUsoComercial() { 
        return usoComercial; 
    }
}