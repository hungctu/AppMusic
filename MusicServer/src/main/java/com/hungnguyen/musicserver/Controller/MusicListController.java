package com.hungnguyen.musicserver.Controller;

import com.hungnguyen.musicserver.Entity.MusicList;
import com.hungnguyen.musicserver.Repository.MusicListRepository;
import com.hungnguyen.musicserver.Services.MusicListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/server")
public class MusicListController {

    @Autowired
    private MusicListService service;

    @PostMapping("/playlist")
    public List<MusicList> Getmusiclist(@ModelAttribute("userId") int id) {
       return service.Getmusiclist(id);
    }

    @PostMapping("/taoplaylist")
    public String taoplaylist(@ModelAttribute("musiclist") MusicList row,
                                      @ModelAttribute("musicid") int musicid) {
        return service.taoplaylist(row, musicid);
    }
}
