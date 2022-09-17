package ru.yandex.practicum.catsgram.exceptions;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException() {
    }

    public UserAlreadyExistException(final String message) {
        super(message);
    }
}
