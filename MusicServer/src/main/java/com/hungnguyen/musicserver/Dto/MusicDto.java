package com.hungnguyen.musicserver.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude
public class MusicDto {
    private Integer musicId;

    private Integer singerId;

    private Integer categoryId;

    private String singerName;

    private String musicName;

    private String musicImages;

    private String url;

    private String lyrics;
}
