package com.palabraventura.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias para la clase Game.
 */
public class GameTest {
    private Game game;
    private Player player;
    private World world1;
    private World world2;

    @BeforeEach
    void setUp() {
        List<Question> questions = Arrays.asList(
                new Question("¿Palabra rara?", Arrays.asList("Mesa", "Epítome", "Luz"), 1, QuestionType.RARE_WORD)
        );

        Level level1 = new Level(questions);
        Level level2 = new Level(questions);

        world1 = new World("Sinónimos", Arrays.asList(level1));
        world2 = new World("Antónimos", Arrays.asList(level2));

        player = new Player("Juan");
        game = new Game(player, Arrays.asList(world1, world2));
    }

    @Test
    void testGetPlayerReturnsCorrectPlayer() {
        assertEquals("Juan", game.getPlayer().getName());
    }

    @Test
    void testGetCurrentWorldReturnsFirst() {
        assertEquals(world1, game.getCurrentWorld());
    }

    @Test
    void testAdvanceWorldIncrementsIndex() {
        game.advanceWorld();
        assertEquals(1, game.getCurrentWorldIndex());
        assertEquals(world2, game.getCurrentWorld());
    }

    @Test
    void testAdvanceWorldDoesNotExceedBounds() {
        game.advanceWorld(); // index = 1
        game.advanceWorld(); // no hay más
        assertEquals(1, game.getCurrentWorldIndex());
    }

    @Test
    void testHasMoreWorldsTrueInitially() {
        assertTrue(game.hasMoreWorlds());
    }

    @Test
    void testHasMoreWorldsFalseAtLastWorld() {
        game.advanceWorld();
        assertFalse(game.hasMoreWorlds());
    }

    @Test
    void testShouldUnlockMinigameFalseInitially() {
        assertFalse(game.shouldUnlockMinigame());
    }

    @Test
    void testShouldUnlockMinigameTrueWhenEnoughPoints() {
        for (int i = 0; i < 10; i++) {
            player.addPoint();
        }
        assertTrue(game.shouldUnlockMinigame());
    }

    @Test
    void testIsGameFinishedFalseInitially() {
        assertFalse(game.isGameFinished());
    }

    @Test
    void testIsGameFinishedTrueWhenLastWorldAndNoMoreLevels() {
        game.advanceWorld(); // move to last world
        world2.advanceLevel(); // no más niveles disponibles (solo tenía uno)
        assertFalse(world2.hasMoreLevels()); // validamos que no hay más niveles
        assertTrue(game.isGameFinished());
    }

    @Test
    void testResetGameRestartsEverything() {
        game.advanceWorld();
        player.useAttempt();
        game.resetGame();

        assertEquals(0, game.getCurrentWorldIndex());
        assertEquals(3, player.getRemainingAttempts());
        assertEquals(0, world1.getCurrentLevelIndex());
        assertEquals(0, world2.getCurrentLevelIndex());
    }

    @Test
    void testGetTotalWorldsReturnsCorrectValue() {
        assertEquals(2, game.getTotalWorlds());
    }
}
