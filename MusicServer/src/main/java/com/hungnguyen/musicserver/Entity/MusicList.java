package com.hungnguyen.musicserver.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "musiclist")
public class MusicList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_list_id")
    private Integer musicListId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "music_list_name")
    private String musicListName;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "music_list_date_create")
    private Date musicListDateCreate;

    public MusicList(Integer userId, String musicListName, Date musicListDateCreate) {
        this.userId = userId;
        this.musicListName = musicListName;
        this.musicListDateCreate = musicListDateCreate;
    }

}
