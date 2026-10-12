package escenario01.producto;

import java.math.BigDecimal;

// ConcreteProduct: requiere tonelaje y número de ejes.
// Regla: $60 + 1.2% del avalúo + $25 por tonelada + $15 por eje.

public class Camion extends Vehiculo {

    // 1) Constantes de la regla de negocio
    private static final BigDecimal TARIFA_BASE = new BigDecimal("60.00");
    private static final BigDecimal TASA_AVALUO = new BigDecimal("0.012");
    private static final BigDecimal VALOR_POR_TONELADA = new BigDecimal("25.00");
    private static final BigDecimal VALOR_POR_EJE = new BigDecimal("15.00");

    // 2) Atributos propios del camión
    private final double tonelaje;
    private final int numeroEjes;

    // 3) Constructor: datos comunes + datos propios + validaciones
    public Camion(String placa, String marca, String modelo, int anio, BigDecimal avaluo,
                  double tonelaje, int numeroEjes) {
        super(placa, marca, modelo, anio, avaluo);
        if (tonelaje <= 0)
            throw new IllegalArgumentException("El tonelaje debe ser mayor a cero");
        if (numeroEjes < 2)
            throw new IllegalArgumentException("Un camión tiene al menos 2 ejes");
        this.tonelaje = tonelaje;
        this.numeroEjes = numeroEjes;
    }

    // 4) Cumplir el contrato
    @Override
    public BigDecimal calcularCostoMatricula() {
        BigDecimal total = TARIFA_BASE
                .add(getAvaluo().multiply(TASA_AVALUO))
                .add(VALOR_POR_TONELADA.multiply(BigDecimal.valueOf(tonelaje)))
                .add(VALOR_POR_EJE.multiply(BigDecimal.valueOf(numeroEjes)));

        return redondear(total);
    }

    @Override
    public String getTipo() {
        return "Camión";
    }

    // 5) Getters de los atributos propios
    public double getTonelaje() { 
        return tonelaje; 
    }

    public int getNumeroEjes() { 
        return numeroEjes; 
    }
}