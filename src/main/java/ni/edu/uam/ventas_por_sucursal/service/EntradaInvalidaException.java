package ni.edu.uam.ventas_por_sucursal.service;

public class EntradaInvalidaException extends Exception {
    public EntradaInvalidaException(String mensaje) {
        super(mensaje);
    }
}