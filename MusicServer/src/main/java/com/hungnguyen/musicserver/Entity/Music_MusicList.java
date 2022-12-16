package com.hungnguyen.musicserver.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "music_musiclist")
public class Music_MusicList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mml_id")
    private Integer mmlId;

    @Column(name = "music_id")
    private Integer musicId;

    @Column(name = "music_list_id")
    private Integer musicListId;

    public Music_MusicList(Integer musicId, Integer musicListId) {
        this.musicId = musicId;
        this.musicListId = musicListId;
    }
}
