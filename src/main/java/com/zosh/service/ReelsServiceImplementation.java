package com.zosh.service;

import com.zosh.models.Reels;
import com.zosh.models.User;
import com.zosh.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReelsServiceImplementation implements ReelsService{
    @Autowired
    private ReelsRepository reelsRepository;
    @Autowired
    private UserService userService;
    @Override
    public Reels createReel(Reels reels, User user) {
        Reels createReel = new Reels();
        createReel.setTitle(reels.getTitle());
        createReel.setUser(user);
        createReel.setVideo(reels.getVideo());
        return reelsRepository.save(createReel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUserReels(Integer userId) throws Exception {
//        User user = userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }
}
