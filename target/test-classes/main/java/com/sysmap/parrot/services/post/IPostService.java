package com.sysmap.parrot.services.post;

import java.util.List;

public interface IPostService {
    String createPost(CreatePostRequest request);
    String likePost(String postId) throws Exception;
    String removeLike(String postId) throws Exception;
    String addComment(String postId, String content) throws Exception;
    String deleteComment(String postId, String commentId) throws Exception;
    FindPostResponse findPostById(String postId) throws Exception;
    List<FindPostResponse> findPosts();
    List<FindPostResponse> findPostsByUserId() throws Exception;
    String deletePost(String postId) throws Exception;
}
