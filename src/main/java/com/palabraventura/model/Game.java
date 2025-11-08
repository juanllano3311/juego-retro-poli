package com.palabraventura.model;

import java.util.List;

/**
 * Clase principal que representa el estado general del juego Palabraventura.
 * Gestiona al jugador, los mundos y el progreso total del juego.
 */
public class Game {
    private final Player player;
    private final List<World> worlds;
    private int currentWorldIndex;

    private static final int POINTS_TO_UNLOCK_MINIGAME = 10;

    /**
     * Constructor del juego, recibe el jugador y los mundos configurados.
     * @param player Jugador del juego
     * @param worlds Lista de mundos (Sinónimos, Antónimos, Palabras Raras)
     */
    public Game(Player player, List<World> worlds) {
        this.player = player;
        this.worlds = worlds;
        this.currentWorldIndex = 0;
    }

    /**
     * Devuelve el mundo actual en el que se encuentra el jugador.
     * @return Mundo actual
     */
    public World getCurrentWorld() {
        if (currentWorldIndex < worlds.size()) {
            return worlds.get(currentWorldIndex);
        }
        return null;
    }

    /**
     * Avanza al siguiente mundo, si está disponible.
     */
    public void advanceWorld() {
        if (hasMoreWorlds()) {
            currentWorldIndex++;
        }
    }

    /**
     * Verifica si hay más mundos disponibles.
     * @return true si no se ha llegado al último mundo
     */
    public boolean hasMoreWorlds() {
        return currentWorldIndex < worlds.size() - 1;
    }

    /**
     * Verifica si el jugador ha ganado acceso al minijuego secreto.
     * @return true si tiene 10 o más puntos
     */
    public boolean shouldUnlockMinigame() {
        return player.getScore() >= POINTS_TO_UNLOCK_MINIGAME;
    }

    /**
     * Verifica si el juego ha finalizado (último mundo y último nivel completados).
     * @return true si el jugador terminó todo el juego
     */
    public boolean isGameFinished() {
        return currentWorldIndex == worlds.size() - 1 &&
                !getCurrentWorld().hasMoreLevels();
    }

    public Player getPlayer() {
        return player;
    }

    public int getCurrentWorldIndex() {
        return currentWorldIndex;
    }

    public int getTotalWorlds() {
        return worlds.size();
    }

    /**
     * Reinicia todo el juego desde el principio.
     */
    public void resetGame() {
        this.currentWorldIndex = 0;
        for (World world : worlds) {
            world.reset();
        }
        player.resetAttempts();
    }
}
