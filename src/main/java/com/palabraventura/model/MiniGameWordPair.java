package com.palabraventura.model;

public class MiniGameWordPair {
    private final String word;
    private final String match;

    public MiniGameWordPair(String word, String match) {
        this.word = word;
        this.match = match;
    }

    public String getWord() {
        return word;
    }

    public String getMatch() {
        return match;
    }
}
