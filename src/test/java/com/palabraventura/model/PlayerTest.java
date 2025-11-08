package com.palabraventura.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias para la clase Player.
 */
public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Juan");
    }

    @Test
    void testNameIsStoredCorrectly() {
        assertEquals("Juan", player.getName());
    }

    @Test
    void testInitialScoreIsZero() {
        assertEquals(0, player.getScore());
    }

    @Test
    void testAddPointIncreasesScore() {
        player.addPoint();
        assertEquals(1, player.getScore());
        player.addPoint();
        assertEquals(2, player.getScore());
    }

    @Test
    void testInitialAttemptsAreThree() {
        assertEquals(3, player.getRemainingAttempts());
    }

    @Test
    void testUseAttemptReducesAttempts() {
        player.useAttempt();
        assertEquals(2, player.getRemainingAttempts());
        player.useAttempt();
        assertEquals(1, player.getRemainingAttempts());
    }

    @Test
    void testAttemptsDoNotGoNegative() {
        player.useAttempt();
        player.useAttempt();
        player.useAttempt();
        player.useAttempt(); // llamada extra
        assertEquals(0, player.getRemainingAttempts());
    }

    @Test
    void testResetAttemptsRestoresToMax() {
        player.useAttempt();
        player.useAttempt();
        player.resetAttempts();
        assertEquals(3, player.getRemainingAttempts());
    }

    @Test
    void testHasNoAttemptsLeftWhenZero() {
        player.useAttempt();
        player.useAttempt();
        player.useAttempt();
        assertTrue(player.hasNoAttemptsLeft());
    }

    @Test
    void testHasNoAttemptsLeftWhenNotZero() {
        player.useAttempt();
        assertFalse(player.hasNoAttemptsLeft());
    }
}
