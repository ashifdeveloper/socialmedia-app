package com.zosh.service;

import com.zosh.models.Comment;
import org.hibernate.annotations.Comments;

public interface CommentService {
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;
    public Comment findCommentById(Integer commentId) throws Exception;
    public Comment likeComment(Integer commentId,Integer userId) throws Exception;
}
