package escenario01.producto;

import java.math.BigDecimal;

// ConcreteProduct: Considera avalúo y antigüedad.
// Regla: $30 + 1.5% del avalúo, con descuento por antigüedad
// (10% desde 5 años, 20% desde 10 años).

public class Auto extends Vehiculo {

    private static final BigDecimal TARIFA_BASE = new BigDecimal("30.00");
    private static final BigDecimal TASA_AVALUO = new BigDecimal("0.015");

    public Auto(String placa, String marca, String modelo, int anio, BigDecimal avaluo) {
        super(placa, marca, modelo, anio, avaluo);
    }

    @Override
    public BigDecimal calcularCostoMatricula() {
        BigDecimal impuesto = getAvaluo().multiply(TASA_AVALUO);

        BigDecimal descuento;
        if (getAntiguedad() >= 10)      
            descuento = new BigDecimal("0.20");
        else if (getAntiguedad() >= 5)  
            descuento = new BigDecimal("0.10");
        else                            
            descuento = BigDecimal.ZERO;
        impuesto = impuesto.multiply(BigDecimal.ONE.subtract(descuento));
        return redondear(TARIFA_BASE.add(impuesto));
    }

    @Override
    public String getTipo() { 
        return "Auto"; 
    }
}