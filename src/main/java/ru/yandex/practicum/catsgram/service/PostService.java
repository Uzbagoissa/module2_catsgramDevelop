package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final List<Post> posts = new ArrayList<>();

    public List<Post> findAll() {
        return posts;
    }

    public Post create(Post post) {
        posts.add(post);
        return post;
    }

    //Вариант метода способом с обходом коллекции через поток
    public Optional<Post> findById(int postId) {
        return posts.stream()
                .filter(x -> x.getId() == postId)
                .findFirst();
    }

    //Вариант метода обычным способом с обходом коллекции через цикл
    /*public Optional<Post> findById(int postId) {
        Post x = null;
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId() == postId){
                x = posts.get(i);
                break;
            }
        }
        return Optional.ofNullable(x);
    }*/
}