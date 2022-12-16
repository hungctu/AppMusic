package com.hungnguyen.musicserver.Services;

import com.hungnguyen.musicserver.Entity.MusicList;
import com.hungnguyen.musicserver.Entity.Music_MusicList;
import com.hungnguyen.musicserver.Repository.MMLRepository;
import com.hungnguyen.musicserver.Repository.MusicListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Service
public class MusicListService {

    @Autowired
    private MusicListRepository musiclist;

    @Autowired
    private MMLRepository mmlRepository;

    public List<MusicList> Getmusiclist(int id) {
        if (musiclist.getmusiclist(id).size() == 0) {
            Date date = new Date();
            MusicList musicList = new MusicList(id,"Bài hát yêu thích",date);
            musiclist.save(musicList);
        }
        return musiclist.getmusiclist(id);
    }

    public String taoplaylist(MusicList row, int musicid) {
        String success = "";
        Date date = new Date();
        MusicList ml = new MusicList(row.getUserId(), row.getMusicListName(),date);
        if (ResponseEntity.ok(musiclist.save(ml)).hasBody()) {

            List<MusicList> musicLists = musiclist.getmusiclisttheoten(ml.getUserId(),ml.getMusicListName());
            int id = musicLists.get(0).getMusicListId();

            Music_MusicList mml = new Music_MusicList(musicid, id);
            if (ResponseEntity.ok(mmlRepository.save(mml)).hasBody()) {
                success = "success";
            } else {
                success = "";
            }

        } else {
            success = "thatbai";
        }
        return "[{\"success\":\"" + success + "\"}]";

    }
}
