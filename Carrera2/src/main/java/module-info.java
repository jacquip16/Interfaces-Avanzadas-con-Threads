module com.pilli.carrera2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.pilli.carrera2 to javafx.fxml;
    exports com.pilli.carrera2;
}
