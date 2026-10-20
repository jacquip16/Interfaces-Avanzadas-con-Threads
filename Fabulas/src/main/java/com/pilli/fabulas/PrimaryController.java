package com.pilli.fabulas;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class PrimaryController {

    @FXML private Button btnIniciar;
    @FXML private Label lblEstadoLiebre;
    @FXML private ProgressBar pgLiebre;
    @FXML private Label lblEstadoTortuga;
    @FXML private ProgressBar pgTortuga;

    @FXML
    private void btnIniciar_click() {
        btnIniciar.setDisable(true);
        pgLiebre.setProgress(0.0);
        pgTortuga.setProgress(0.0);

        // Hilo de la Liebre
        Thread hiloLiebre = new Thread(() -> {
            try {
                for (int i = 1; i <= 50; i++) {
                    final double progreso = (double) i / 100;
                    Thread.sleep(40); // Corre rápido al inicio
                    Platform.runLater(() -> {
                        pgLiebre.setProgress(progreso);
                        lblEstadoLiebre.setText("La Liebre: ¡Está corriendo!");
                    });
                }

                // La liebre se duerme a la mitad del camino
                Platform.runLater(() -> lblEstadoLiebre.setText("La Liebre: ¡Se durmió!"));
                Thread.sleep(5000); // Duerme 5 segundos

                // Despierta y corre hacia la meta
                for (int i = 51; i <= 100; i++) {
                    final double progreso = (double) i / 100;
                    Thread.sleep(60);
                    Platform.runLater(() -> {
                        pgLiebre.setProgress(progreso);
                        lblEstadoLiebre.setText("La Liebre: ¡Despertó y corre!");
                    });
                }
                Platform.runLater(() -> lblEstadoLiebre.setText("La Liebre: ¡Llegó a la meta!"));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Hilo de la Tortuga
        Thread hiloTortuga = new Thread(() -> {
            try {
                for (int i = 1; i <= 100; i++) {
                    final double progreso = (double) i / 100;
                    Thread.sleep(90); // Paso constante y lento
                    Platform.runLater(() -> {
                        pgTortuga.setProgress(progreso);
                        lblEstadoTortuga.setText("La Tortuga: ¡Está corriendo!");
                    });
                }
                Platform.runLater(() -> {
                    lblEstadoTortuga.setText("La Tortuga: ¡Llegó a la meta!");
                    btnIniciar.setDisable(false); // Reactivar botón al terminar
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Iniciar ambos hilos al mismo tiempo
        hiloLiebre.start();
        hiloTortuga.start();
    }
}