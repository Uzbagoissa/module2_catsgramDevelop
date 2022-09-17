package ru.yandex.practicum.catsgram.exceptions;

public class InvalidEmailException extends Exception {
    public InvalidEmailException() {
    }

    public InvalidEmailException(final String message) {
        super(message);
    }
}
