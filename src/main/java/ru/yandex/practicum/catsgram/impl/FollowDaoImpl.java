package ru.yandex.practicum.catsgram.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.dao.FollowDao;
import ru.yandex.practicum.catsgram.dao.PostDao;
import ru.yandex.practicum.catsgram.dao.UserDao;
import ru.yandex.practicum.catsgram.model.Follow;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class FollowDaoImpl implements FollowDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;
    private final PostDao postDao;

    public FollowDaoImpl(JdbcTemplate jdbcTemplate, UserDao userDao, PostDao postDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
        this.postDao = postDao;
    }

    @Override
    public List<Post> getFollowFeed(String userId, int max) {
        // получаем все подписки пользователя
        String sql = "select * from cat_follow where follower_id = ?";
        List<Follow> follows = jdbcTemplate.query(sql, (rs, rowNum) -> makeFollow(rs), userId);

        // выгружаем авторов на которых подписан пользователь
        Set<User> authors = follows.stream()
                .map(p -> userDao.findUserById(p.getAuthor()))
                .filter(p -> p.isPresent())
                .map(p -> p.get())
                .collect(Collectors.toSet());

        if(authors.isEmpty()) {
            return Collections.emptyList();
        }

        // выгружаем посты полученных выше авторов
        return authors.stream()
                .map(postDao::findPostsByUser)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                .limit(max)
                .collect(Collectors.toList());
    }

    private Follow makeFollow(ResultSet rs) throws SQLException {
        String authorID = rs.getString("author_id");
        String followerID = rs.getString("follower_id");
        return new Follow(authorID, followerID);// реализуйте маппинг результата запроса в объект класса Follow
    }
}
