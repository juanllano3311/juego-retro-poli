package com.palabraventura.model;

/**
 * Clase que representa al jugador en el juego Palabraventura.
 * Controla su nombre, puntaje acumulado y número de intentos por nivel.
 */
public class Player {
    private final String name;
    private int score;
    private int remainingAttempts;

    private static final int MAX_ATTEMPTS_PER_LEVEL = 3;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.remainingAttempts = MAX_ATTEMPTS_PER_LEVEL;
    }

    public void addPoint() {
        this.score++;
    }

    public void addScore(int puntos) {
        this.score += puntos;
    }

    public void useAttempt() {
        if (remainingAttempts > 0) {
            remainingAttempts--;
        }
    }

    public void resetAttempts() {
        this.remainingAttempts = MAX_ATTEMPTS_PER_LEVEL;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    public boolean hasNoAttemptsLeft() {
        return remainingAttempts == 0;
    }
}
