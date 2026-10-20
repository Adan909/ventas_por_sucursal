package ni.edu.uam.ventas_por_sucursal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ni.edu.uam.ventas_por_sucursal.model.ResultadoSucursal;
import ni.edu.uam.ventas_por_sucursal.model.Venta;
import ni.edu.uam.ventas_por_sucursal.service.CalculadorVentas;
import ni.edu.uam.ventas_por_sucursal.service.EntradaInvalidaException;
import ni.edu.uam.ventas_por_sucursal.service.ParserVentas;

import java.util.List;

public class HelloController {

    @FXML
    private TextArea txtEntrada;

    @FXML
    private Label lblResultado;

    private final ParserVentas parser = new ParserVentas();
    private final CalculadorVentas calculador = new CalculadorVentas();

    @FXML
    protected void onCalcular() {
        try {
            List<Venta> ventas = parser.parsear(txtEntrada.getText());
            ResultadoSucursal r = calculador.obtenerGanadora(ventas);
            lblResultado.setStyle("-fx-text-fill: black; -fx-font-size: 16px;");
            lblResultado.setText(r.toString());
        } catch (EntradaInvalidaException e) {
            lblResultado.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            lblResultado.setText(e.getMessage());
        }
    }

    @FXML
    protected void onLimpiar() {
        txtEntrada.clear();
        lblResultado.setText("");
    }
}