module ni.edu.uam.ventas_por_sucursal {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ventas_por_sucursal to javafx.fxml;
    exports ni.edu.uam.ventas_por_sucursal;
}