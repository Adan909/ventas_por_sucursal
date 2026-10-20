package ni.edu.uam.ventas_por_sucursal.service;

import ni.edu.uam.ventas_por_sucursal.model.Venta;

import java.util.ArrayList;
import java.util.List;

public class ParserVentas {

    // Restricciones: 1 < N < 100000  y  1 < importe < 1000000
    // Si tus límites son inclusivos, cambia MIN a 1 y usa <= en las comparaciones.
    public static final int MIN_N = 1;
    public static final int MAX_N = 100_000;
    public static final long MIN_IMPORTE = 1;
    public static final long MAX_IMPORTE = 1_000_000;

    public List<Venta> parsear(String texto) throws EntradaInvalidaException {
        if (texto == null || texto.isBlank()) {
            throw new EntradaInvalidaException("La entrada está vacía.");
        }

        String[] tokens = texto.trim().split("\\s+");

        int n;
        try {
            n = Integer.parseInt(tokens[0]);
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException("N debe ser un entero.");
        }

        if (n <= MIN_N || n >= MAX_N) {
            throw new EntradaInvalidaException(
                    "N debe cumplir " + MIN_N + " < N < " + MAX_N + ".");
        }

        if (tokens.length != 1 + 2 * n) {
            throw new EntradaInvalidaException(
                    "Se esperaban " + n + " líneas 'sucursal importe', pero los datos no coinciden.");
        }

        List<Venta> ventas = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            String sucursal = tokens[1 + 2 * i];
            String textoImporte = tokens[2 + 2 * i];

            // Solo letras ASCII (A-Z, a-z), sin espacios, números ni símbolos
            if (!sucursal.matches("[A-Za-z]+")) {
                throw new EntradaInvalidaException(
                        "Sucursal inválida en la venta " + (i + 1) + ": solo letras ASCII sin espacios.");
            }

            long importe;
            try {
                importe = Long.parseLong(textoImporte);
            } catch (NumberFormatException e) {
                throw new EntradaInvalidaException(
                        "Importe inválido en la venta " + (i + 1) + ".");
            }

            if (importe <= MIN_IMPORTE || importe >= MAX_IMPORTE) {
                throw new EntradaInvalidaException(
                        "Importe fuera de rango en la venta " + (i + 1) + ".");
            }

            ventas.add(new Venta(sucursal, importe));
        }
        return ventas;
    }
}