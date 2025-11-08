package com.palabraventura.controller;

import com.palabraventura.model.MiniGameWordPair;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class MinigameController {

    @FXML private GridPane gridPane;
    @FXML private Label statusLabel;
    @FXML private Label scoreLabel; // NUEVO: para mostrar el puntaje
    @FXML private Button backButton;

    private final List<MiniGameWordPair> pairs = Arrays.asList(
            new MiniGameWordPair("Feliz", "Contento"),
            new MiniGameWordPair("Rápido", "Veloz"),
            new MiniGameWordPair("Casa", "Hogar"),
            new MiniGameWordPair("Grande", "Enorme")
    );

    private Map<Button, String> buttonWordMap = new HashMap<>();
    private Button firstSelected = null;
    private int score;

    // Llamado desde GameController para pasar el puntaje
    public void setInitialScore(int score) {
        this.score = score;
        actualizarScoreLabel();
    }

    @FXML
    public void initialize() {
        List<String> words = new ArrayList<>();
        for (MiniGameWordPair pair : pairs) {
            words.add(pair.getWord());
            words.add(pair.getMatch());
        }

        Collections.shuffle(words);

        int index = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 2; col++) {
                String word = words.get(index++);
                Button button = new Button(word);
                button.setPrefSize(120, 50);
                button.setOnAction(e -> handleSelection(button));
                gridPane.add(button, col, row);
                buttonWordMap.put(button, word);
            }
        }

        actualizarScoreLabel(); // mostrar desde el inicio
    }

    private void handleSelection(Button selected) {
        if (selected.isDisable()) return;

        if (firstSelected == null) {
            firstSelected = selected;
            selected.setStyle("-fx-background-color: lightblue;");
        } else {
            String firstWord = buttonWordMap.get(firstSelected);
            String secondWord = buttonWordMap.get(selected);

            boolean isMatch = pairs.stream().anyMatch(p ->
                    (p.getWord().equals(firstWord) && p.getMatch().equals(secondWord)) ||
                            (p.getWord().equals(secondWord) && p.getMatch().equals(firstWord))
            );

            if (isMatch) {
                statusLabel.setText("¡Correcto!");
                firstSelected.setDisable(true);
                selected.setDisable(true);
                score += 5; // SUMA por acierto
            } else {
                statusLabel.setText("Intenta de nuevo");
                score -= 2; // RESTA por error
            }

            firstSelected.setStyle("");
            firstSelected = null;

            actualizarScoreLabel();

            if (buttonWordMap.keySet().stream().allMatch(Button::isDisable)) {
                statusLabel.setText("🎉 ¡Ganaste! Puntaje final: " + score);
            }
        }
    }

    private void actualizarScoreLabel() {
        if (scoreLabel != null) {
            scoreLabel.setText("Puntaje: " + score);
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/palabraventura/view/start-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene scene = new Scene(root, 600, 400); // Asegura el mismo tamaño que al inicio
            stage.setScene(scene);
            stage.setTitle("Palabraventura - Menú Principal");
            stage.centerOnScreen(); // Opcional para centrar la ventana
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
