package com.hungnguyen.musicserver.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Music {

    @Id
    @GeneratedValue
    @Column(name = "music_id")
    private Integer musicId;

    @Column(name = "singer_id")
    private Integer singerId;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "music_name")
    private String musicName;

    @Column(name = "music_images")
    private String musicImages;

    private String url;

    private String lyrics;

    @OneToMany(targetEntity = RecommendedMusic.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "music_id",referencedColumnName = "music_id")
    private List<RecommendedMusic> rmusics;

    @OneToMany(targetEntity = Music_MusicList.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "music_id",referencedColumnName = "music_id")
    private List<Music_MusicList> mmls;
}
