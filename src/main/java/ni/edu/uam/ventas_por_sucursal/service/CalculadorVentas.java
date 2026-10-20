package ni.edu.uam.ventas_por_sucursal.service;

import ni.edu.uam.ventas_por_sucursal.model.ResultadoSucursal;
import ni.edu.uam.ventas_por_sucursal.model.Venta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculadorVentas {

    public ResultadoSucursal obtenerGanadora(List<Venta> ventas) {
        Map<String, Long> totales = new HashMap<>();
        for (Venta v : ventas) {
            totales.merge(v.sucursal(), v.importe(), Long::sum);
        }

        String mejorSucursal = null;
        long mejorTotal = Long.MIN_VALUE;

        for (Map.Entry<String, Long> e : totales.entrySet()) {
            String sucursal = e.getKey();
            long total = e.getValue();

            if (total > mejorTotal
                    || (total == mejorTotal && sucursal.compareTo(mejorSucursal) < 0)) {
                mejorSucursal = sucursal;
                mejorTotal = total;
            }
        }
        return new ResultadoSucursal(mejorSucursal, mejorTotal);
    }
}