module com.pilli.conteo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.pilli.conteo to javafx.fxml;
    exports com.pilli.conteo;
}
