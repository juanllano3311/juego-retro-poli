package com.palabraventura.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias para la clase Level.
 */
public class LevelTest {
    private Level level;
    private List<Question> questions;

    @BeforeEach
    void setUp() {
        questions = Arrays.asList(
                new Question("¿Cuál es el antónimo de feliz?",
                        Arrays.asList("Contento", "Triste", "Emocionado"),
                        1,
                        QuestionType.ANTONYM),
                new Question("¿Qué palabra es rara?",
                        Arrays.asList("Epítome", "Carro", "Mesa"),
                        0,
                        QuestionType.RARE_WORD)
        );
        level = new Level(questions);
    }

    @Test
    void testInitialIndexIsZero() {
        assertEquals(0, level.getCurrentIndex());
    }

    @Test
    void testGetCurrentQuestionReturnsFirstInitially() {
        Question current = level.getCurrentQuestion();
        assertNotNull(current);
        assertEquals("¿Cuál es el antónimo de feliz?", current.getQuestionText());
    }

    @Test
    void testAnswerCorrectlyReturnsTrue() {
        boolean result = level.answerCurrentQuestion(1); // respuesta correcta
        assertTrue(result);
    }

    @Test
    void testAnswerIncorrectlyReturnsFalse() {
        boolean result = level.answerCurrentQuestion(0); // respuesta incorrecta
        assertFalse(result);
    }

    @Test
    void testAnswerAdvancesToNextQuestion() {
        level.answerCurrentQuestion(1);
        assertEquals(1, level.getCurrentIndex());
        Question next = level.getCurrentQuestion();
        assertNotNull(next);
        assertEquals("¿Qué palabra es rara?", next.getQuestionText());
    }

    @Test
    void testHasNextQuestionTrueWhenNotAtEnd() {
        assertTrue(level.hasNextQuestion());
    }

    @Test
    void testHasNextQuestionFalseWhenAtEnd() {
        level.answerCurrentQuestion(1);
        level.answerCurrentQuestion(0); // ya respondió las 2
        assertFalse(level.hasNextQuestion());
    }

    @Test
    void testResetRestartsToFirstQuestion() {
        level.answerCurrentQuestion(1);
        level.reset();
        assertEquals(0, level.getCurrentIndex());
        assertEquals("¿Cuál es el antónimo de feliz?", level.getCurrentQuestion().getQuestionText());
    }

    @Test
    void testGetTotalQuestionsReturnsCorrectCount() {
        assertEquals(2, level.getTotalQuestions());
    }

    @Test
    void testGetCurrentQuestionReturnsNullWhenBeyondLimit() {
        level.answerCurrentQuestion(1);
        level.answerCurrentQuestion(0);
        assertNull(level.getCurrentQuestion());
    }
}
