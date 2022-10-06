package ru.yandex.practicum.catsgram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.catsgram.exception.*;
import ru.yandex.practicum.catsgram.service.PostService;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.Map;

@RestControllerAdvice(assignableTypes = {PostController.class, PostFeedController.class, UserController.class, UserService.class, PostService.class})
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handle(final IncorrectParameterException e) {
        return Map.of(
                "error", "Чет там произошло",
                "errorMessage", e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handle(final InvalidEmailException e) {
        return Map.of(
                "error", "Адрес электронной почты не может быть пустым.",
                "errorMessage", e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handle(final PostNotFoundException e) {
        return Map.of(
                "error", "Пост № %d не найден",
                "errorMessage", e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handle(final UserAlreadyExistException e) {
        return Map.of(
                "error", "Пользователь с электронной почтой %s уже зарегистрирован.",
                "errorMessage", e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handle(final UserNotFoundException e) {
        return Map.of(
                "error", "Пользователь %s не найден",
                "errorMessage", e.getMessage()
        );
    }

    public class ErrorResponse {
        String error;
        String description;

        public ErrorResponse(String error, String description) {
            this.error = error;
            this.description = description;
        }

        public String getError() {
            return error;
        }

        public String getDescription() {
            return description;
        }
    }
}
