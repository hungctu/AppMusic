package com.hungnguyen.musicserver.Services;

import com.hungnguyen.musicserver.Entity.Music_MusicList;
import com.hungnguyen.musicserver.Repository.MMLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MMLService {

    @Autowired
    private MMLRepository repository;

    public String xoabaihat(int musicid,int ml_id) {
        String success = "";
        if(repository.Xoabaihatkhoidanhsach(musicid, ml_id)==1) {
            success ="success";
        }else {

        }
        return "[{\"success\":\"" + success + "\"}]";
    }

    public String thembaihat(int musicid,int ml_id) {
        String success = "";

        //System.out.println(mapper.ktmusic_musiclist(musiclist));

        if(repository.ktmusic_musiclist(musicid, ml_id) !=null) {
            success="DaTonTai";
        }else {
            if(ResponseEntity.ok(repository.save(new Music_MusicList(musicid,ml_id))).hasBody()) {
                success ="success";
            }else {

            }
        }
        return "[{\"success\":\"" + success + "\"}]";
    }
}
