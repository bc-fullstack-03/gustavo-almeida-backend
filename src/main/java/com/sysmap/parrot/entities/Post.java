package com.sysmap.parrot.entities;

import com.sysmap.parrot.entities.embedded.Comment;
import com.sysmap.parrot.entities.embedded.Like;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Data
public class Post {
    @Id
    private UUID id;
    private UUID userId;
    private LocalDateTime createdAt;
    private String content;
    private ArrayList<Like> likes;
    private ArrayList<Comment> comments;

    public Post(UUID userId, String content) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
        this.content = content;
        this.likes = new ArrayList<Like>();
        this.comments = new ArrayList<Comment>();
    }

}
