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
@Table(name="recommended_music")
public class RecommendedMusic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rm_id")
    private Integer rmId;

    @Column(name = "music_id")
    private Integer musicId;

    @Column(name = "emotion_id")
    private Integer emotionId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "total_view")
    private Integer totalView;

    public RecommendedMusic(Integer musicId, Integer userId, Integer emotionId) {
        this.musicId = musicId;
        this.emotionId = emotionId;
        this.userId = userId;
    }
}
