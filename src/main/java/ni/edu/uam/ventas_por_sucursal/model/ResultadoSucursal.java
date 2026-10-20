package ni.edu.uam.ventas_por_sucursal.model;

public record ResultadoSucursal(String sucursal, long total) {
    @Override
    public String toString() {
        return sucursal + " " + total;
    }
}