package com.sysmap.parrot.services.post;

import com.sysmap.parrot.entities.Post;
import com.sysmap.parrot.entities.User;
import com.sysmap.parrot.entities.embedded.Comment;
import com.sysmap.parrot.repository.PostRepository;
import com.sysmap.parrot.entities.embedded.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostService implements IPostService {
    @Autowired
    private PostRepository postRepository;

    private User getAuthenticatedUser() {
       return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
    public String createPost(CreatePostRequest request) {
        var user = getAuthenticatedUser();
        var post = new Post(user.getId(), request.content);
        postRepository.save(post);
        return post.getId().toString();
    }

    public String likePost(String postId) throws Exception{
        var user = getAuthenticatedUser();
        var post = postRepository.findPostById(UUID.fromString(postId)).get();
        if (post == null) {
            throw new Exception("Post not found");
        }
        var like = new Like(user.getId());
        post.getLikes().add(like);
        postRepository.save(post);
        return post.getId().toString();
    }

    public String removeLike(String postId) throws Exception {
        var user = getAuthenticatedUser();
        var post = postRepository.findPostById(UUID.fromString(postId)).get();
        if (post.getLikes().isEmpty()) {
            throw new Exception("No likes found");
        }
        var like = post.getLikes().stream().filter(c -> c.getUserId().equals(user.getId())).findFirst().get();

        if (!like.getUserId().equals(user.getId())) {
            throw new Exception("User not allowed to remove like");
        }
        post.getLikes().remove(like);
        postRepository.save(post);
        return post.toString();
    }

    public String addComment(String postId, String content) throws Exception {
        var user = getAuthenticatedUser();
        var post = postRepository.findPostById(UUID.fromString(postId)).orElse(null);
        if (post == null) {
            throw new Exception("Post not found");
        }
        var comment = new Comment(user.getId(), content);
        post.getComments().add(comment);
        postRepository.save(post);
        return post.toString();
    }
    public String deleteComment(String postId, String commentId) throws Exception {
        var user = getAuthenticatedUser();
        var post = postRepository.findPostById(UUID.fromString(postId)).orElse(null);
        if (post == null) {
            throw new Exception("Post not found");
        }
        var comment = post.getComments().stream().filter(c -> c.getCommentId().equals(UUID.fromString(commentId))).findFirst().get();
        if (!comment.getCommentId().equals(user.getId())) {
           throw new Exception("User not allowed to delete comment");
        }
        post.getComments().remove(comment);
        postRepository.save(post);
        return post.toString();

    }

    public FindPostResponse findPostById(String postId) throws Exception {
        var id = UUID.fromString(postId);
        var post = postRepository.findPostById(id).get();
        if (post == null){
            throw new Exception("Post not found");
        }
        var response = new FindPostResponse(
                post.getId(),
                post.getUserId(),
                post.getCreatedAt(),
                post.getContent(),
                post.getLikes(),
                post.getComments());
        return response;
    }

    public List<FindPostResponse> findPosts() {
        var posts = postRepository.findAll();
        List<FindPostResponse> postsList = new ArrayList<>();
        for (Post post : posts) {
            var postResponse = new FindPostResponse(
                    post.getId(),
                    post.getUserId(),
                    post.getCreatedAt(),
                    post.getContent(),
                    post.getLikes(),
                    post.getComments());
            postsList.add(postResponse);
        }
        return postsList;
    }
    public List<FindPostResponse> findPostsByUserId() throws Exception {
        var user = getAuthenticatedUser();

        if (postRepository.findPostsByUserId(user.getId()).isEmpty()) {
            throw new Exception("User does not exist");
        }

        var posts = postRepository.findPostsByUserId(user.getId());

        List<FindPostResponse> postsList = new ArrayList<>();
        for (Post post : posts) {
            var postResponse = new FindPostResponse(
                    post.getId(),
                    post.getUserId(),
                    post.getCreatedAt(),
                    post.getContent(),
                    post.getLikes(),
                    post.getComments());
            postsList.add(postResponse);
        }
        return postsList;
    }

    public String deletePost(String postId) throws Exception{
        var user = getAuthenticatedUser();
        var post = postRepository.findPostById(UUID.fromString(postId)).get();
        if (post == null) {
            throw new Exception("Post not found");
        }
        if (!user.getId().equals(post.getId())) {
            throw new Exception("User not allowed to delete post");
        }
        postRepository.delete(post);
        return "Post deleted!";
    }

}
