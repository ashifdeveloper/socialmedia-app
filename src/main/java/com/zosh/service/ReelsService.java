package com.zosh.service;

import com.zosh.models.Reels;
import com.zosh.models.User;

import java.util.List;

public interface ReelsService{
    public Reels createReel(Reels reels, User user);
    public List<Reels> findAllReels();
    public List<Reels> findUserReels(Integer userId) throws Exception;
}
