package com.palabraventura.model;

import java.util.List;

/**
 * Clase que representa un mundo del juego, como Sinónimos, Antónimos o Palabras Raras.
 * Cada mundo contiene una lista de niveles.
 */
public class World {
    private final String name;
    private final List<Level> levels;
    private int currentLevelIndex;

    /**
     * Constructor que inicializa un mundo con un nombre y una lista de niveles.
     * @param name Nombre del mundo (ej. "Sinónimos")
     * @param levels Lista de niveles asociados a este mundo
     */
    public World(String name, List<Level> levels) {
        this.name = name;
        this.levels = levels;
        this.currentLevelIndex = 0;
    }

    /**
     * Devuelve el nivel actual en el que se encuentra el jugador.
     * @return Nivel actual
     */
    public Level getCurrentLevel() {
        if (currentLevelIndex < levels.size()) {
            return levels.get(currentLevelIndex);
        }
        return null;
    }

    /**
     * Avanza al siguiente nivel del mundo.
     */
    public void advanceLevel() {
        if (hasMoreLevels()) {
            currentLevelIndex++;
        }
    }

    /**
     * Verifica si hay más niveles disponibles en el mundo.
     * @return true si hay niveles restantes
     */
    public boolean hasMoreLevels() {
        return currentLevelIndex < levels.size() - 1;
    }

    /**
     * Reinicia el mundo desde el primer nivel.
     */
    public void reset() {
        this.currentLevelIndex = 0;
        for (Level level : levels) {
            level.reset();
        }
    }

    public String getName() {
        return name;
    }

    public int getCurrentLevelIndex() {
        return currentLevelIndex;
    }

    public int getTotalLevels() {
        return levels.size();
    }
}
