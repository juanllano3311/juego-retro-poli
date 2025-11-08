package com.palabraventura.controller;

import com.palabraventura.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class GameController {

    private Game game;
    private Level currentLevel;
    private Question currentQuestion;

    @FXML private Label questionLabel;
    @FXML private Label scoreLabel;
    @FXML private Label messageLabel;
    @FXML private Button optionButton1;
    @FXML private Button optionButton2;
    @FXML private Button optionButton3;
    @FXML private Button optionButton4;
    @FXML private Button nextButton;

    @FXML
    public void initialize() {
        iniciarJuego();
        cargarNivelActual();
    }

    private void iniciarJuego() {
        Player player = new Player("Jugador1");

        // Preguntas de cada mundo
        Level sinonimosLevel = new Level(Arrays.asList(
                new Question("¿Sinónimo de CASA?", Arrays.asList("Hogar", "Perro", "Flor", "Gato"), 0, QuestionType.SINONYM),
                new Question("¿Sinónimo de RÁPIDO?", Arrays.asList("Lento", "Ágil", "Pequeño", "Viejo"), 1, QuestionType.SYNONYM)
        ));

        Level antonimosLevel = new Level(Arrays.asList(
                new Question("¿Antónimo de GATO?", Arrays.asList("León", "Tigre", "Perro", "Ratón"), 3, QuestionType.SYNONYM),
                new Question("¿Antónimo de ALTO?", Arrays.asList("Grande", "Medio", "Pequeño", "Bajo"), 3, QuestionType.SYNONYM)
        ));

        Level palabrasRarasLevel = new Level(Arrays.asList(
                new Question("¿Qué significa LUNA?", Arrays.asList("Sol", "Satélite", "Estrella", "Planeta"), 1, QuestionType.SYNONYM),
                new Question("¿Qué significa EPIFANÍA?", Arrays.asList("Fiesta", "Revelación", "Mentira", "Sombras"), 1, QuestionType.SYNONYM)
        ));

        World mundo1 = new World("Sinónimos", List.of(sinonimosLevel));
        World mundo2 = new World("Antónimos", List.of(antonimosLevel));
        World mundo3 = new World("Palabras Raras", List.of(palabrasRarasLevel));

        game = new Game(player, Arrays.asList(mundo1, mundo2, mundo3));
    }

    private void cargarNivelActual() {
        currentLevel = game.getCurrentWorld().getCurrentLevel();
        currentQuestion = currentLevel.getCurrentQuestion();

        questionLabel.setText(currentQuestion.getQuestionText());
        List<String> options = currentQuestion.getOptions();
        optionButton1.setText(options.get(0));
        optionButton2.setText(options.get(1));
        optionButton3.setText(options.get(2));
        optionButton4.setText(options.get(3));

        habilitarOpciones(true);
        messageLabel.setText("");
        nextButton.setDisable(true);
        actualizarPuntaje();
    }

    private void evaluarRespuesta(int selectedIndex) {
        boolean correcto = currentQuestion.isCorrect(selectedIndex);
        if (correcto) {
            messageLabel.setText("¡Correcto!");
            messageLabel.setStyle("-fx-text-fill: green;");
            game.getPlayer().addScore(10);
        } else {
            messageLabel.setText("Incorrecto.");
            messageLabel.setStyle("-fx-text-fill: red;");
            game.getPlayer().addScore(-5);
        }

        habilitarOpciones(false);
        nextButton.setDisable(false);
        actualizarPuntaje();
    }

    private void habilitarOpciones(boolean estado) {
        optionButton1.setDisable(!estado);
        optionButton2.setDisable(!estado);
        optionButton3.setDisable(!estado);
        optionButton4.setDisable(!estado);
    }

    private void actualizarPuntaje() {
        scoreLabel.setText("Puntaje: " + game.getPlayer().getScore());
    }

    @FXML private void handleOption1() { evaluarRespuesta(0); }
    @FXML private void handleOption2() { evaluarRespuesta(1); }
    @FXML private void handleOption3() { evaluarRespuesta(2); }
    @FXML private void handleOption4() { evaluarRespuesta(3); }

    @FXML
    private void handleNextWord() {
        currentLevel.answerCurrentQuestion(0);

        if (currentLevel.hasNextQuestion()) {
            cargarNivelActual();
        } else if (game.getCurrentWorld().hasMoreLevels()) {
            game.getCurrentWorld().advanceLevel();
            cargarNivelActual();
        } else if (game.hasMoreWorlds()) {
            game.advanceWorld();
            cargarNivelActual();
        } else {
            cargarMiniJuego();
        }
    }

    private void cargarMiniJuego() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/palabraventura/view/minigame-view.fxml"));
            Parent root = loader.load();

            MinigameController controller = loader.getController();
            controller.setInitialScore(game.getPlayer().getScore());

            Stage stage = (Stage) questionLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
