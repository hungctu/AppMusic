package com.hungnguyen.musicserver.Services;

import com.hungnguyen.musicserver.Entity.Advertisement;
import com.hungnguyen.musicserver.Repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdService {

    @Autowired
    private AdRepository ad;

    public List<Advertisement> findAll() {
        return ad.findAll();
    }
}
