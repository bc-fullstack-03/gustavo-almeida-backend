package com.sysmap.parrot.api;

import com.sysmap.parrot.services.post.CreatePostRequest;
import com.sysmap.parrot.services.post.FindPostResponse;
import com.sysmap.parrot.services.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parrot/post")
public class PostController {

    @Autowired
    private IPostService postService;

    @PostMapping("createPost")
    public ResponseEntity<String> createPost(@RequestBody CreatePostRequest request) {
        var response = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("addLike")
    public ResponseEntity<String> addLike(String postId) {
        try {
            return ResponseEntity.ok().body(postService.likePost(postId));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("removeLike")
    public ResponseEntity<String> removeLike(String postId) {
        try {
            return ResponseEntity.ok().body(postService.removeLike(postId));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("addComment")
    public ResponseEntity<String> addComment(String postId, String content) {
        try {
            return ResponseEntity.ok().body(postService.addComment(postId, content));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("deleteComment")
    public ResponseEntity<String> deleteComment(String postId, String commentId) {
        try {
            return ResponseEntity.ok().body(postService.deleteComment(postId, commentId));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("findPostById")
    public ResponseEntity<FindPostResponse> findPostById(String postId) {
        try {
            return ResponseEntity.ok().body(postService.findPostById(postId));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("findPosts")
    public ResponseEntity<List<FindPostResponse>> findPosts() {
        return ResponseEntity.ok().body(postService.findPosts());
    }

    @GetMapping("findPostsByUser")
    public ResponseEntity<List<FindPostResponse>> findPostsByUser() {
        try {
            return ResponseEntity.ok().body(postService.findPostsByUserId());
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("deletePost")
    public ResponseEntity<String> deletePost(String postId) {
        try {
            return ResponseEntity.ok().body(postService.deletePost(postId));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
