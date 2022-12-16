package com.hungnguyen.musicserver.Controller;

import com.hungnguyen.musicserver.Entity.RecommendedMusic;
import com.hungnguyen.musicserver.Services.RMusicService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class RMusicController {

    @Autowired
    private RMusicService service;

    @Transactional
    @PostMapping("/capnhatluotnghe")
    public String Capnhatluotnghe(@ModelAttribute("musicId") int mid,@ModelAttribute("userId") int uid) {
        return service.Capnhatluotnghe(mid, uid);
    }

    @Transactional
    @PostMapping("/capnhatcamxuc")
    public String Capnhatcamxuc(@ModelAttribute("musicId") int mid,@ModelAttribute("userId") int uid,@ModelAttribute("emotionId") int eid) {
        return service.Capnhatcamxuc(mid, uid, eid);
    }

    @Transactional
    @PostMapping("/capnhatcamxuc2")
    public String Capnhatcamxuc2(@ModelAttribute("musicId") int mid,@ModelAttribute("userId") int uid,@ModelAttribute("emotionId") int eid) {
        return service.Capnhatcamxuc2(mid, uid, eid);
    }
}
