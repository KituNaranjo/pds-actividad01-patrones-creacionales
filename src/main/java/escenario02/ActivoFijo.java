package escenario02;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

// Product (Builder): Activo fijo inmutable. Solo se crea mediante
// ActivoFijo.Builder; no existen constructores públicos sobrecargados.

public final class ActivoFijo {

    private final String codigo;
    private final String descripcion;
    private final BigDecimal precioAdquisicion;
    // Opcionales
    private final String marca;
    private final String modelo;
    private final String numeroSerie;
    private final String ubicacion;
    private final String custodio;
    private final LocalDate fechaAdquisicion;
    private final Integer garantiaMeses;
    private final String proveedor;
    private final Map<String, String> caracteristicasTecnicas;
    private final String observaciones;

    private ActivoFijo(Builder b) {
        this.codigo = b.codigo;
        this.descripcion = b.descripcion;
        this.precioAdquisicion = b.precioAdquisicion;
        this.marca = b.marca;
        this.modelo = b.modelo;
        this.numeroSerie = b.numeroSerie;
        this.ubicacion = b.ubicacion;
        this.custodio = b.custodio;
        this.fechaAdquisicion = b.fechaAdquisicion;
        this.garantiaMeses = b.garantiaMeses;
        this.proveedor = b.proveedor;
        this.caracteristicasTecnicas = Collections.unmodifiableMap(new LinkedHashMap<>(b.caracteristicasTecnicas));
        this.observaciones = b.observaciones;
    }

    public static Builder builder(String codigo, String descripcion, BigDecimal precioAdquisicion) {
        return new Builder(codigo, descripcion, precioAdquisicion);
    }

    public String getCodigo() { 
        return codigo; 
    }

    public String getDescripcion() { 
        return descripcion; 
    }

    public BigDecimal getPrecioAdquisicion() { 
        return precioAdquisicion; 
    }

    public Optional<String> getMarca() { 
        return Optional.ofNullable(marca); 
    }

    public Optional<String> getModelo() { 
        return Optional.ofNullable(modelo); 
    }

    public Optional<String> getNumeroSerie() { 
        return Optional.ofNullable(numeroSerie); 
    }

    public Optional<String> getUbicacion() { 
        return Optional.ofNullable(ubicacion);
    }

    public Optional<String> getCustodio() { 
        return Optional.ofNullable(custodio); 
    }

    public Optional<LocalDate> getFechaAdquisicion() { 
        return Optional.ofNullable(fechaAdquisicion); 
    }

    public Optional<Integer> getGarantiaMeses() { 
        return Optional.ofNullable(garantiaMeses); 
    }

    public Optional<String> getProveedor() { 
        return Optional.ofNullable(proveedor); 
    }

    public Map<String, String> getCaracteristicasTecnicas() { 
        return caracteristicasTecnicas; 
    }

    public Optional<String> getObservaciones() { 
        return Optional.ofNullable(observaciones); 
    }

    public Optional<LocalDate> getFinGarantia() {
        return getFechaAdquisicion().flatMap(f -> getGarantiaMeses().map(f::plusMonths));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Activo ").append(codigo).append(" - ").append(descripcion)
          .append(" ($").append(precioAdquisicion).append(")");
        getMarca().ifPresent(v -> sb.append("\n    Marca: ").append(v));
        getModelo().ifPresent(v -> sb.append("\n    Modelo: ").append(v));
        getNumeroSerie().ifPresent(v -> sb.append("\n    Serie: ").append(v));
        getUbicacion().ifPresent(v -> sb.append("\n    Ubicación: ").append(v));
        getCustodio().ifPresent(v -> sb.append("\n    Custodio: ").append(v));
        getFechaAdquisicion().ifPresent(v -> sb.append("\n    Adquirido: ").append(v));
        getGarantiaMeses().ifPresent(v -> sb.append("\n    Garantía: ").append(v).append(" meses"));
        getFinGarantia().ifPresent(v -> sb.append(" (hasta ").append(v).append(")"));
        getProveedor().ifPresent(v -> sb.append("\n    Proveedor: ").append(v));
        caracteristicasTecnicas.forEach((k, v) -> sb.append("\n    ").append(k).append(": ").append(v));
        getObservaciones().ifPresent(v -> sb.append("\n    Observaciones: ").append(v));
        return sb.toString();
    }

    public static final class Builder {

        private final String codigo;
        private final String descripcion;
        private final BigDecimal precioAdquisicion;
        // Opcionales
        private String marca;
        private String modelo;
        private String numeroSerie;
        private String ubicacion;
        private String custodio;
        private LocalDate fechaAdquisicion;
        private Integer garantiaMeses;
        private String proveedor;
        private final Map<String, String> caracteristicasTecnicas = new LinkedHashMap<>();
        private String observaciones;

        private Builder(String codigo, String descripcion, BigDecimal precioAdquisicion) {
            this.codigo = requerirTexto(codigo, "código institucional");
            this.descripcion = requerirTexto(descripcion, "nombre o descripción");
            this.precioAdquisicion = Objects.requireNonNull(precioAdquisicion,
                    "El precio de adquisición es obligatorio");
        }

        public Builder conMarca(String marca) { 
            this.marca = marca; 
            return this; 
        }

        public Builder conModelo(String modelo) { 
            this.modelo = modelo; 
            return this; 
        }

        public Builder conNumeroSerie(String numeroSerie) { 
            this.numeroSerie = numeroSerie; 
            return this; 
        }

        public Builder conUbicacion(String ubicacion) { 
            this.ubicacion = ubicacion; 
            return this; 
        }

        public Builder conCustodio(String custodio) { 
            this.custodio = custodio; 
            return this; 
        }

        public Builder conFechaAdquisicion(LocalDate fecha) { 
            this.fechaAdquisicion = fecha; 
            return this; 
        }

        public Builder conGarantiaMeses(int meses) { 
            this.garantiaMeses = meses; 
            return this; 
        }

        public Builder conProveedor(String proveedor) { 
            this.proveedor = proveedor; 
            return this; 
        }

        public Builder conObservaciones(String observaciones) { 
            this.observaciones = observaciones;
            return this; 
        }

        public Builder conCaracteristica(String nombre, String valor) {
            caracteristicasTecnicas.put(requerirTexto(nombre, "nombre de la característica"), valor);
            return this;
        }

        public ActivoFijo build() {
            if (precioAdquisicion.signum() <= 0)
                throw new IllegalStateException("El precio de adquisición debe ser mayor a cero");
            if (garantiaMeses != null && garantiaMeses <= 0)
                throw new IllegalStateException("La garantía debe ser mayor a cero meses");
            if (fechaAdquisicion != null && fechaAdquisicion.isAfter(LocalDate.now()))
                throw new IllegalStateException("La fecha de adquisición no puede ser futura");
            return new ActivoFijo(this);
        }

        private static String requerirTexto(String valor, String campo) {
            if (valor == null || valor.isBlank())
                throw new IllegalArgumentException("El campo '" + campo + "' es obligatorio");
            return valor.trim();
        }
    }
}