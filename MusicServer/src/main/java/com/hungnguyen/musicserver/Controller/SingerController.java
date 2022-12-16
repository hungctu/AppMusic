package com.hungnguyen.musicserver.Controller;

import com.hungnguyen.musicserver.Dto.SingerDto;
import com.hungnguyen.musicserver.Entity.Singer;
import com.hungnguyen.musicserver.Repository.SingerRepository;
import com.hungnguyen.musicserver.Services.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/server")
public class SingerController {

    @Autowired
    private SingerService service;

    @PostMapping("/timkiemcasi")
    public List<SingerDto> timkiemcasi(@ModelAttribute("singername") String singername) {
        return service.timkiemcasi(singername);
    }

}
