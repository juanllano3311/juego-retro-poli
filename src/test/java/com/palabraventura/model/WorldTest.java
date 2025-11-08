package com.palabraventura.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias para la clase World.
 */
public class WorldTest {
    private World world;
    private Level level1;
    private Level level2;

    @BeforeEach
    void setUp() {
        // Reutilizamos preguntas sencillas para niveles de prueba
        List<Question> questions1 = Arrays.asList(
                new Question("Pregunta 1", Arrays.asList("a", "b", "c"), 0, QuestionType.SINONYM)
        );

        List<Question> questions2 = Arrays.asList(
                new Question("Pregunta 2", Arrays.asList("x", "y", "z"), 1, QuestionType.SINONYM)
        );

        level1 = new Level(questions1);
        level2 = new Level(questions2);

        world = new World("Sinónimos", Arrays.asList(level1, level2));
    }

    @Test
    void testGetNameReturnsCorrectly() {
        assertEquals("Sinónimos", world.getName());
    }

    @Test
    void testInitialLevelIsZero() {
        assertEquals(0, world.getCurrentLevelIndex());
    }

    @Test
    void testGetCurrentLevelReturnsLevelOne() {
        Level current = world.getCurrentLevel();
        assertNotNull(current);
        assertEquals(level1, current);
    }

    @Test
    void testAdvanceLevelUpdatesCurrentIndex() {
        world.advanceLevel();
        assertEquals(1, world.getCurrentLevelIndex());
        assertEquals(level2, world.getCurrentLevel());
    }

    @Test
    void testAdvanceLevelDoesNotExceedLimit() {
        world.advanceLevel(); // index = 1
        world.advanceLevel(); // index = 2 (no debe avanzar)
        assertEquals(1, world.getCurrentLevelIndex());
    }

    @Test
    void testHasMoreLevelsTrueInitially() {
        assertTrue(world.hasMoreLevels());
    }

    @Test
    void testHasMoreLevelsFalseAtLastLevel() {
        world.advanceLevel(); // index = 1 (último)
        assertFalse(world.hasMoreLevels());
    }

    @Test
    void testGetTotalLevelsReturnsCorrectValue() {
        assertEquals(2, world.getTotalLevels());
    }

    @Test
    void testResetResetsLevelIndexAndLevels() {
        // Avanzar y responder para marcar progreso
        world.advanceLevel();
        level1.answerCurrentQuestion(0);
        assertEquals(1, world.getCurrentLevelIndex());
        assertEquals(1, level1.getCurrentIndex());

        world.reset();

        assertEquals(0, world.getCurrentLevelIndex());
        assertEquals(0, level1.getCurrentIndex());
        assertEquals(0, level2.getCurrentIndex());
    }
}
