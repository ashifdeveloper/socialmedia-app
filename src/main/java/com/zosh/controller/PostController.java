package com.zosh.controller;

import com.zosh.models.Post;
import com.zosh.models.User;
import com.zosh.response.ApiResponse;
import com.zosh.service.PostService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt, @RequestBody Post post) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post createdPost = postService.createNewPost(post,reqUser.getId());

        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);

    }
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        String message = postService.deletePost(postId,reqUser.getId());
        ApiResponse res = new ApiResponse(message,true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post>findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);

    }
    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId){
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }


    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPost(){
        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @PutMapping("/posts/save/{postId}")
    public ResponseEntity<Post>savedPostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.savedPost(postId,reqUser.getId());
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);

    }


    @PutMapping("/posts/like/{postId}")
    public ResponseEntity<Post>likePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);

        Post post = postService.likePost(postId,reqUser.getId());
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);

    }
}

