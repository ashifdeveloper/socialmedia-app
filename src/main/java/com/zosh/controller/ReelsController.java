package com.zosh.controller;

import com.zosh.models.Reels;
import com.zosh.models.User;
import com.zosh.service.ReelsService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {
    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;
    @PostMapping("/reels")
    public Reels createReels(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt){
        User reqUser = userService.findUserByJwt(jwt);
        Reels createdReels = reelsService.createReel(reels,reqUser);

        return createdReels;

    }

    @GetMapping("/reels")
    public List<Reels> createReels(){
        List<Reels> reels = reelsService.findAllReels();

        return reels;

    }

    @GetMapping("/reels/user/{userId}")
    public List<Reels> findUserReels(@PathVariable Integer userId) throws Exception {
        List<Reels> reels = reelsService.findUserReels(userId);

        return reels;

    }
}
