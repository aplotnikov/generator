package org.home.generator.configuration;

public enum BinaryOperations {
    PLUS('+'),
    MINUS('-'),
    MULTIPLY('*'),
    DIVIDE('/');

    private final char view;

    BinaryOperations(char view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return String.valueOf(view);
    }
}
