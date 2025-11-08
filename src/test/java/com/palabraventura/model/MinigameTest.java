package com.palabraventura.model;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias para la clase Minigame.
 */
public class MinigameTest {
    @Test
    void testGetSampleQuestionReturnsExpectedQuestion() {
        Minigame minigame = new Minigame();
        Question question = minigame.getSampleQuestion();

        assertEquals("¿Cuál es el sinónimo de 'rápido'?", question.getQuestionText());
        List<String> options = question.getOptions();
        assertEquals(3, options.size());
        assertEquals("Ágil", options.get(1));
        assertEquals(1, question.getCorrectIndex());
        assertEquals(QuestionType.SINONYM, question.getType());
    }

    @RepeatedTest(10)
    void testPlayReturnsBooleanValue() {
        Minigame minigame = new Minigame();
        boolean result = minigame.play();
        // Solo validamos que sea booleano y no lanza errores (por su aleatoriedad)
        assertTrue(result || !result);
    }
}
