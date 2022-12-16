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
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Integer CategoryId;

    @Column(name = "category_name")
    private String CategoryName;

    @Column(name = "category_images")
    private String CategoryImages;

    /*@OneToMany(targetEntity = Music.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    private List<Music> musics;*/
}
