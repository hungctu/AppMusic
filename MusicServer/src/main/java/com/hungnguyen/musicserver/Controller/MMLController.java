package com.hungnguyen.musicserver.Controller;

import com.hungnguyen.musicserver.Services.MMLService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/server")
public class MMLController {

    @Autowired
    private MMLService service;

    @Transactional
    @PostMapping("/xoabaihat")
    public String xoabaihat(@ModelAttribute("musicId") int musicId,@ModelAttribute("musicListId") int musicListId) {
        return service.xoabaihat(musicId, musicListId);
    }

    @PostMapping("/thembaihat")
    public String thembaihat(@ModelAttribute("musicId") int musicId,@ModelAttribute("musicListId") int musicListId) {

        return service.thembaihat(musicId, musicListId);
    }
}
