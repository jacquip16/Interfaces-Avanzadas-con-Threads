module com.pilli.fabulas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.pilli.fabulas to javafx.fxml;
    exports com.pilli.fabulas;
}
