package com.pilli.conteo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML private TextField txtClics;
    @FXML private Button btnContar;
    
    @FXML private ProgressBar pgBar;
    @FXML private Button btnEsperar;

    private int contadorClics = 0;

    @FXML
    private void btnContar_click() {
        contadorClics++;
        txtClics.setText(String.valueOf(contadorClics));
    }

    @FXML
    private void btnEsperar_click() {
        // Deshabilitar el botón mientras se ejecuta la espera
        btnEsperar.setDisable(true);
        pgBar.setProgress(0.0);

        // Crear e iniciar el Thread secundario
        Thread hiloEspera = new Thread(() -> {
            int totalPasos = 100; // Dividido en 100 incrementos
            int tiempoTotalMs = 10000; // 10 segundos
            int tiempoPasoMs = tiempoTotalMs / totalPasos; // 100 ms por paso

            for (int i = 1; i <= totalPasos; i++) {
                final double progreso = (double) i / totalPasos;

                try {
                    Thread.sleep(tiempoPasoMs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Actualizar la interfaz gráfica usando Platform.runLater
                Platform.runLater(() -> pgBar.setProgress(progreso));
            }

            // Al terminar el conteo, volver a habilitar el botón
            Platform.runLater(() -> btnEsperar.setDisable(false));
        });

        hiloEspera.start();
    }
}