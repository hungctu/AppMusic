package com.hungnguyen.musicserver.Repository;

import com.hungnguyen.musicserver.Dto.MusicDto;
import com.hungnguyen.musicserver.Dto.SingerDto;
import com.hungnguyen.musicserver.Entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SingerRepository extends JpaRepository<Singer,Integer> {

    @Query("select new com.hungnguyen.musicserver.Dto.MusicDto(m.musicId,s.singerId,m.categoryId," +
            "s.singerName,m.musicName,m.musicImages,m.url,m.lyrics) from Singer s join s.musics m join m.rmusics r " +
            "where  m.singerId=s.singerId and m.musicId=r.musicId GROUP BY r.musicId ORDER BY SUM(r.totalView) desc")
    public List<MusicDto> gettopmusic();

    @Query("select new com.hungnguyen.musicserver.Dto.MusicDto(m.musicId,s.singerId,m.categoryId,"
            +"s.singerName,m.musicName,m.musicImages,m.url,m.lyrics) from Singer s join s.musics m where " +
            "m.singerId=s.singerId and m.musicId=:music_id")
    public MusicDto getRanmusic(@Param("music_id") int music_id);

    @Query("select new com.hungnguyen.musicserver.Dto.MusicDto(m.musicId,s.singerId,m.categoryId,"
            +"s.singerName,m.musicName,m.musicImages,m.url,m.lyrics) from Singer s join s.musics m where " +
            "m.singerId=s.singerId and m.categoryId=:category_id")
    public List<MusicDto> baihattheotheloai(@Param("category_id") int category_id);

    @Query("select new com.hungnguyen.musicserver.Dto.MusicDto(m.musicId,s.singerId,m.categoryId,"
            +"s.singerName,m.musicName,m.musicImages,m.url,m.lyrics) from Singer s join s.musics m where " +
            "m.singerId=s.singerId and s.singerId=:singer_id")
    public List<MusicDto> baihattheocasi(@Param("singer_id") int id);

    @Query("select new com.hungnguyen.musicserver.Dto.MusicDto(m.musicId,s.singerId,m.categoryId,"
            +"s.singerName,m.musicName,m.musicImages,m.url,m.lyrics) from Singer s join s.musics m where " +
            "m.singerId=s.singerId and (m.musicName like %:timkiem% or s.singerName like %:timkiem%)")
    public List<MusicDto> timkiembaihat(@Param("timkiem") String timkiem);

    @Query("select new com.hungnguyen.musicserver.Dto.MusicDto(m.musicId,s.singerId,m.categoryId," +
            "s.singerName,m.musicName,m.musicImages,m.url,m.lyrics) from Singer s join s.musics m join m.rmusics r " +
            "where  m.singerId=s.singerId and m.musicId=r.musicId " +
            "and r.userId=:user_id GROUP BY r.musicId ORDER BY SUM(r.totalView) desc")
    public List<MusicDto> baihatusernghenhieu(@Param("user_id") int id);

    @Query("select new com.hungnguyen.musicserver.Dto.MusicDto(m.musicId,s.singerId,m.categoryId," +
            "s.singerName,m.musicName,m.musicImages,m.url,m.lyrics) from Singer s join s.musics m join m.mmls r " +
            "where  m.singerId=s.singerId and m.musicId=r.musicId " +
            "and r.musicListId=:music_list_id")
    public List<MusicDto> baihattheoplaylist(@Param("music_list_id") int id);

    @Query("select new com.hungnguyen.musicserver.Dto.MusicDto(m.musicId,s.singerId,m.categoryId," +
            "s.singerName,m.musicName,m.musicImages,m.url,m.lyrics) from Singer s join s.musics m join m.rmusics r " +
            "where  m.singerId=s.singerId and m.musicId=r.musicId " +
            "and r.emotionId=:emotion_id GROUP BY r.musicId ORDER BY SUM(r.totalView) desc")
    public List<MusicDto> baihatnghetheocamxuc(@Param("emotion_id") int id);

    @Query("select new com.hungnguyen.musicserver.Dto.MusicDto(m.musicId,s.singerId,m.categoryId," +
            "s.singerName,m.musicName,m.musicImages,m.url,m.lyrics) from Singer s join s.musics m join m.rmusics r " +
            "where  m.singerId=s.singerId and m.musicId=r.musicId and r.userId=:user_id and" +
            " r.emotionId=:emotion_id GROUP BY r.musicId ORDER BY r.totalView desc")
    public List<MusicDto> baihatusernghetheocamxuc(@Param("user_id") int user_id,@Param("emotion_id") int id);

    @Query("SELECT new com.hungnguyen.musicserver.Dto.SingerDto(s.singerId,s.singerName,s.singerImages) FROM Singer s WHERE s.singerName LIKE %:timkiem%")
    public List<SingerDto> timkiemcasi(@Param("timkiem") String timkiem);

}
