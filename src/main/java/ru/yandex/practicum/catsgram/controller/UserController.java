package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final ArrayList<User> users = new ArrayList<>();

    @GetMapping()
    public ArrayList<User> usersMap() {
        return users;
    }

    @PostMapping()
    public void postUser(@RequestBody User user) throws UserAlreadyExistException, InvalidEmailException {
        if (users.contains(user)){
            throw new UserAlreadyExistException("Пользователь уже был добавлен!");
        } else if (user.getEmail().equals("")){
            throw new InvalidEmailException("Отстутствует email пользователя!");
        } else {
            users.add(user);
        }
    }

    @PutMapping()
    public void putUser(@RequestBody User user) throws InvalidEmailException {
        if (user.getEmail().equals("")){
            throw new InvalidEmailException("Отстутствует email пользователя!");
        } else if(users.contains(user)) {
            users.add(user);
        } else {
            users.add(user);
        }
    }

}
