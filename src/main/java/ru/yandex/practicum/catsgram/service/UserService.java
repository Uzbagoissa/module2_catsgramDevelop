package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.*;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }

    public User create(User user) {
        if(user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Адрес электронной почты не может быть пустым.");
        }
        if(users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с электронной почтой " +
                    user.getEmail() + " уже зарегистрирован.");
        }
        users.put(user.getEmail(), user);
        return user;
    }

    public User put(User user) {
        if(user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Адрес электронной почты не может быть пустым.");
        }
        users.put(user.getEmail(), user);
        return user;
    }

    //Вариант метода способом с обходом коллекции через поток
    public Optional<User> findByEmail(String userEmail) {
        return users.values().stream()
                .filter(x -> x.getEmail().equals(userEmail))
                .findFirst();
    }

    //Вариант метода обычным способом с обходом коллекции через цикл
    /*public Optional<User> findByEmail(String userEmail) {
        User x = null;
        for (User value : users.values()) {
            if (value.getEmail().equals(userEmail)){
                x = value;
                break;
            }
        }
        return Optional.ofNullable(x);
    }*/
}
