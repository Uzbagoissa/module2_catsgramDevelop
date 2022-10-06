package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.yandex.practicum.catsgram.model.Post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final List<Post> posts = new ArrayList<>();
    int postID = 1;

    public List<Post> findAll(int size, int from, String sort) {
        return posts.stream()
                .sorted((p0, p1) -> {
            int comp = p0.getCreationDate().compareTo(p1.getCreationDate()); //прямой порядок сортировки
            if(sort.equals("desc")){
                comp = -1 * comp; //обратный порядок сортировки
            }
            return comp;
        })
                .skip(from)
                .limit(size)
                .collect(Collectors.toList());
    }

    public Post create(Post post) {
        post.setId(postID);
        posts.add(post);
        postID++;
        return post;
    }

    //Вариант метода способом с обходом коллекции через поток
    public Optional<Post> findById(int postId) {
        return posts.stream()
                .filter(x -> x.getId() == postId)
                .findFirst();
    }

    public List<Post> findAllByUserEmail(String email, Integer size, String sort) {
        return posts.stream()
                .filter(p -> email.equals(p.getAuthor()))
                .sorted((p0, p1) -> {
            int comp = p0.getCreationDate().compareTo(p1.getCreationDate()); //прямой порядок сортировки
            if(sort.equals("desc")){
                comp = -1 * comp; //обратный порядок сортировки
            }
            return comp;
        })
                .limit(size)
                .collect(Collectors.toList());
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