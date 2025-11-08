package com.palabraventura.model;

import java.util.List;

/**
 * Clase que representa un nivel dentro de un mundo del juego.
 * Contiene una lista de preguntas y gestiona el progreso dentro del nivel.
 */
public class Level {
    private final List<Question> questions;
    private int currentIndex;

    /**
     * Constructor que inicializa un nivel con una lista de preguntas.
     * @param questions Lista de preguntas que pertenecen a este nivel
     */
    public Level(List<Question> questions) {
        this.questions = questions;
        this.currentIndex = 0;
    }

    /**
     * Devuelve la pregunta actual del nivel.
     * @return Pregunta actual
     */
    public Question getCurrentQuestion() {
        if (currentIndex < questions.size()) {
            return questions.get(currentIndex);
        }
        return null;
    }

    /**
     * Procesa la respuesta del jugador y avanza de pregunta.
     * @param selectedIndex Índice seleccionado por el jugador
     * @return true si la respuesta es correcta, false si es incorrecta
     */
    public boolean answerCurrentQuestion(int selectedIndex) {
        Question current = getCurrentQuestion();
        if (current != null) {
            boolean isCorrect = current.isCorrect(selectedIndex);
            currentIndex++;
            return isCorrect;
        }
        return false;
    }

    /**
     * Verifica si quedan preguntas por responder en el nivel.
     * @return true si hay más preguntas
     */
    public boolean hasNextQuestion() {
        return currentIndex < questions.size();
    }

    /**
     * Reinicia el progreso del nivel (vuelve a la primera pregunta).
     */
    public void reset() {
        this.currentIndex = 0;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}
