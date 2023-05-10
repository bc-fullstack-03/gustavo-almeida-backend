package com.sysmap.parrot.services.post;

import com.sysmap.parrot.entities.embedded.Comment;
import com.sysmap.parrot.entities.embedded.Like;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Data
@AllArgsConstructor
public class FindPostResponse {

    public UUID id;
    public UUID userId;
    public LocalDateTime createdAt;
    public String content;
    public ArrayList<Like> likes;
    public ArrayList<Comment> comments;
}
