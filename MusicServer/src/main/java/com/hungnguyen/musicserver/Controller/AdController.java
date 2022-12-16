package com.hungnguyen.musicserver.Controller;

import com.hungnguyen.musicserver.Entity.Advertisement;
import com.hungnguyen.musicserver.Services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/server")
public class AdController {

    @Autowired
    private AdService ad;

    @GetMapping(path = "/advertisement")
    public List<Advertisement> getallAd() {
        return ad.findAll();
    }
}
