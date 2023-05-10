package com.sysmap.parrot.entities.embedded;

import com.sysmap.parrot.entities.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Comment {
    public UUID userId;
    public UUID commentId;
    public LocalDateTime createdAt;
    public String content;

    public Comment(UUID userId, String content) {
        this.userId = userId;
        this.commentId = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.content = content;
    }
}
