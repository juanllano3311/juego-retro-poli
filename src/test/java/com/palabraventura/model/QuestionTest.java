package com.palabraventura.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias para la clase Question.
 */
public class QuestionTest {
    private Question question;
    private List<String> options;

    @BeforeEach
    void setUp() {
        options = Arrays.asList("Lento", "Ágil", "Torpe");
        question = new Question("¿Cuál es el sinónimo de rápido?", options, 1, QuestionType.SINONYM);
    }

    @Test
    void testQuestionTextIsStoredCorrectly() {
        assertEquals("¿Cuál es el sinónimo de rápido?", question.getQuestionText());
    }

    @Test
    void testOptionsAreStoredCorrectly() {
        assertEquals(options, question.getOptions());
        assertEquals(3, question.getOptions().size());
    }

    @Test
    void testCorrectIndexIsStoredCorrectly() {
        assertEquals(1, question.getCorrectIndex());
    }

    @Test
    void testTypeIsStoredCorrectly() {
        assertEquals(QuestionType.SINONYM, question.getType());
    }

    @Test
    void testIsCorrectReturnsTrueForCorrectAnswer() {
        assertTrue(question.isCorrect(1));
    }

    @Test
    void testIsCorrectReturnsFalseForIncorrectAnswer() {
        assertFalse(question.isCorrect(0));
        assertFalse(question.isCorrect(2));
    }
}
