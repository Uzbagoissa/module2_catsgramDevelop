package ru.yandex.practicum.catsgram.model;

import java.time.Instant;

public class Post {
    private Integer id;
    private final String author; // СЮДА КЛАСТЬ EMAIL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private final Instant creationDate = Instant.now(); // дата создания
    private String description; // описание
    private String photoUrl; // url-адрес фотографии

    public void setId(Integer id) {
        this.id = id;
    }

    public Post(Integer id, String author, String description, String photoUrl) {
        this.id = id;
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
    }

    public String getAuthor() {
        return author;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getId() {
        return id;
    }
}