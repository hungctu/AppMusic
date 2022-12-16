package com.hungnguyen.musicserver.Services;

import com.hungnguyen.musicserver.Dto.MusicDto;
import com.hungnguyen.musicserver.Entity.Music;
import com.hungnguyen.musicserver.Repository.MusicRepository;
import com.hungnguyen.musicserver.Repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;
@Service
public class MusicDtoService {

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private MusicRepository music;

    public List<MusicDto> gettopmusic(){
        return singerRepository.gettopmusic().subList(0,10);
    }
    public List<MusicDto> getrandommusic(){
        List<MusicDto> musicDtos = new ArrayList<>();
        List<Music> musicList = music.getrandom();
        for(Music music1 : musicList){
            musicDtos.add(singerRepository.getRanmusic(music1.getMusicId()));
        }
        return musicDtos;
    }

    public List<MusicDto> usernghenhieu(int id){
        if(singerRepository.baihatusernghenhieu(id).size()>20){
            return singerRepository.baihatusernghenhieu(id).subList(0,20);
        }
        else {
            return singerRepository.baihatusernghenhieu(id);
        }
    }

    public List<MusicDto> baihattheotheloai(int ca_id){
        return singerRepository.baihattheotheloai(ca_id);
    }

    public List<MusicDto> baihattheocasi(int id){
        return singerRepository.baihattheocasi(id);
    }

    public List<MusicDto> timkiembaihat(String timkiem){
        return singerRepository.timkiembaihat(timkiem);
    }

    public List<MusicDto> baihattheoplaylist(int id){
        return singerRepository.baihattheoplaylist(id);
    }

    public List<MusicDto> baihatnghetheocamxuc(int id){
        return singerRepository.baihatnghetheocamxuc(id);
    }

    public List<MusicDto> baihatusernghetheocamxuc(int userid,int emotionId){
        return singerRepository.baihatusernghetheocamxuc(userid,emotionId);
    }
}
