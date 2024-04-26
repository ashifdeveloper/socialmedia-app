package com.zosh.repository;

import com.zosh.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
//    @Query("select p from post p where p.users.id=:userId")
    List<Post> findPostByUserId(Integer userId);

}
