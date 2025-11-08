package com.palabraventura.model;

import java.util.Random;

/**
 * Clase que representa el minijuego secreto de Palabraventura.
 * Esta versión inicial es simbólica y puede ser extendida con lógica adicional.
 */
public class Minigame {
    /**
     * Simula la ejecución del minijuego.
     * Por ahora, genera una pregunta aleatoria sencilla.
     * @return true si el jugador "gana", false si "pierde"
     */
    public boolean play() {
        // Lógica simbólica para esta entrega: 50% de probabilidad de ganar
        return new Random().nextBoolean();
    }

    /**
     * Devuelve una pregunta de ejemplo del minijuego.
     * @return Pregunta simulada
     */
    public Question getSampleQuestion() {
        return new Question(
                "¿Cuál es el sinónimo de 'rápido'?",
                java.util.Arrays.asList("Lento", "Ágil", "Pesado"),
                1,
                QuestionType.SINONYM
        );
    }
}
