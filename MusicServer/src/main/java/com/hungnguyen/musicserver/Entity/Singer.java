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
public class Singer {

    @Id
    @GeneratedValue
    @Column(name = "singer_id")
    private Integer singerId;

    @Column(name = "singer_name")
    private String singerName;

    @Column(name = "singer_images")
    private String singerImages;

    @OneToMany(targetEntity = Music.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "singer_id",referencedColumnName = "singer_id")
    private List<Music> musics;
}
