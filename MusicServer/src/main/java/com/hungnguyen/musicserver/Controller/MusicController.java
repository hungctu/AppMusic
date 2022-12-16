package com.hungnguyen.musicserver.Controller;

import com.hungnguyen.musicserver.Dto.MusicDto;
import com.hungnguyen.musicserver.Entity.Music;
import com.hungnguyen.musicserver.Repository.MusicRepository;
import com.hungnguyen.musicserver.Repository.SingerRepository;
import com.hungnguyen.musicserver.Services.MusicDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server")
public class MusicController {

    @Autowired
    private MusicDtoService service;

    @GetMapping("/top10music")
    public List<MusicDto> gettop(){
        return service.gettopmusic();
    }

    @GetMapping("/randommusic")
    public List<MusicDto> getrandommusic(){
        return service.getrandommusic();
    }

    @PostMapping("/baihattheotheloai")
    public List<MusicDto> getbaihattheotheloai(@ModelAttribute("ca_id") int ca_id){
        return service.baihattheotheloai(ca_id);
    }

    @PostMapping("/baihattheocasi")
    public List<MusicDto> getbaihattheocasi(@ModelAttribute("singer_id") int id){
        return service.baihattheocasi(id);
    }

    @PostMapping("/timkiembaihat")
    public List<MusicDto> timkiembaihat(@ModelAttribute("timkiem") String timkiem){
        return service.timkiembaihat(timkiem);
    }

    @PostMapping("/baihatusernghenhieu")
    public List<MusicDto> getbaihatusernghenghieu(@ModelAttribute("user_id") int id){
        return service.usernghenhieu(id);
    }

    @PostMapping("/musicinplaylist")
    public List<MusicDto> getbaihattheoplaylist(@ModelAttribute("playlistid") int id){
        return service.baihattheoplaylist(id);
    }

    @PostMapping("/baihatnghetheocamxuc")
    public List<MusicDto> getbaihatnghetheocamxuc(@ModelAttribute("emotion_id") int id){
        return service.baihatnghetheocamxuc(id);
    }

    @PostMapping("/baihatusernghetheocamxuc")
    public List<MusicDto> getbaihatusernghetheocamxuc(@ModelAttribute("userId") int userid,@ModelAttribute("emotionId") int emotionId){
        return service.baihatusernghetheocamxuc(userid,emotionId);
    }
}
