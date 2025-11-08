package com.palabraventura.model;

import java.util.List;

/**
 * Clase que representa una pregunta de selección múltiple.
 */
public class Question {
    private final String questionText;
    private final List<String> options;
    private final int correctIndex;
    private QuestionType type;


    public Question(String questionText, List<String> options, int correctIndex, QuestionType sinonym) {
        this.questionText = questionText;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public boolean isCorrect(int selectedIndex) {
        return selectedIndex == correctIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
}
