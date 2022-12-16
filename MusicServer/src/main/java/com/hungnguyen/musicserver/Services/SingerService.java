package com.hungnguyen.musicserver.Services;

import com.hungnguyen.musicserver.Dto.SingerDto;
import com.hungnguyen.musicserver.Repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
public class SingerService {

    @Autowired
    private SingerRepository repository;

    public List<SingerDto> timkiemcasi(String singername) {
        return repository.timkiemcasi(singername);
    }
}
