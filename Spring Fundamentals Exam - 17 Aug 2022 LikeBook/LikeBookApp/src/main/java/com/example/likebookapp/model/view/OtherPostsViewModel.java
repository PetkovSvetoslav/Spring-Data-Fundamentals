package com.example.likebookapp.model.view;

import com.example.likebookapp.model.entity.UserEntity;
import com.example.likebookapp.model.entity.enums.MoodNameEnum;

import java.util.Set;

public class OtherPostsViewModel {

    private Long id;

    private String username;
    private MoodNameEnum name;
    private String content;
    private Set<UserEntity> userLikes;

    public OtherPostsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MoodNameEnum getName() {
        return name;
    }

    public void setName(MoodNameEnum name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<UserEntity> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(Set<UserEntity> userLikes) {
        this.userLikes = userLikes;
    }

    public boolean checkIfUserAlreadyLikedThePost(Long userId) {
        return this.getUserLikes()
                .stream()
                .anyMatch(userEntity -> userEntity.getId().equals(userId));
    }


}
