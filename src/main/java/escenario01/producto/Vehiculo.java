package escenario01.producto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Year;

// Product (Factory Method): Contrato común de todos los vehículos.

public abstract class Vehiculo {

    private final String placa;
    private final String marca;
    private final String modelo;
    private final int anioFabricacion;
    private final BigDecimal avaluo;

    protected Vehiculo(String placa, String marca, String modelo, int anioFabricacion, BigDecimal avaluo) {
        if (placa == null || placa.isBlank())
            throw new IllegalArgumentException("La placa es obligatoria");
        if (avaluo == null || avaluo.signum() < 0)
            throw new IllegalArgumentException("El avalúo no puede ser negativo");
        if (anioFabricacion > Year.now().getValue())
            throw new IllegalArgumentException("Año de fabricación inválido");

        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.avaluo = avaluo;
    }

    // Contrato: Cada tipo implementa su propia lógica de cálculo. 
    public abstract BigDecimal calcularCostoMatricula();

    public abstract String getTipo();

    public int getAntiguedad() {
        return Year.now().getValue() - anioFabricacion;
    }

    protected static BigDecimal redondear(BigDecimal valor) {
        return valor.setScale(2, RoundingMode.HALF_UP);
    }

    public String getPlaca() { 
        return placa; 
    }

    public String getMarca() { 
        return marca; 
    }

    public String getModelo() { 
        return modelo; 
    }

    public int getAnioFabricacion() { 
        return anioFabricacion; 
    }

    public BigDecimal getAvaluo() { 
        return avaluo; 
    }

    @Override
    public String toString() {
        return "%s [%s] %s %s %d - avalúo $%s".formatted(
                getTipo(), placa, marca, modelo, anioFabricacion, avaluo);
    }
}