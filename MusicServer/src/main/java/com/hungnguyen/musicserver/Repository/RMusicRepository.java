package com.hungnguyen.musicserver.Repository;

import com.hungnguyen.musicserver.Entity.RecommendedMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RMusicRepository extends JpaRepository<RecommendedMusic,Integer> {

    @Query(value = "select * from recommended_music where user_id =:user_id and music_id =:music_id and emotion_id is null",nativeQuery = true)
    public RecommendedMusic baihatuserdanghe(@Param("user_id") int userid,@Param("music_id") int musicid);

    @Modifying
    @Query(value = "UPDATE recommended_music SET total_view=total_view+1 where user_id =:user_id and music_id =:music_id and emotion_id is null",nativeQuery = true)
    public int capnhatluotngheuser(@Param("user_id") int userid,@Param("music_id") int musicid);

    @Modifying
    @Query(value = "Insert into recommended_music(user_id,music_id,total_view) values(:user_id,:music_id,1)",nativeQuery = true)
    public int themluotngheuser(@Param("user_id") int userid,@Param("music_id") int musicid);

    @Query(value = "select * from recommended_music where user_id is null and music_id =:music_id and emotion_id is null",nativeQuery = true)
    public RecommendedMusic baihatdanghe(@Param("music_id") int musicid);

    @Modifying
    @Query(value = "UPDATE recommended_music SET total_view=total_view+1 where user_id is null and music_id =:music_id and emotion_id is null",nativeQuery = true)
    public int capnhatluotnghe(@Param("music_id") int musicid);

    @Modifying
    @Query(value = "Insert into recommended_music(music_id,total_view) values(:music_id,1)",nativeQuery = true)
    public int themluotnghe(@Param("music_id") int musicid);

    @Modifying
    @Query(value = "UPDATE recommended_music SET total_view=total_view-1 where user_id =:user_id and music_id =:music_id and emotion_id is null",nativeQuery = true)
    public int truluotngheuser(@Param("user_id") int userid,@Param("music_id") int musicid);

    @Query(value = "select * from recommended_music where user_id =:user_id and music_id =:music_id and emotion_id=:emotion_id",nativeQuery = true)
    public RecommendedMusic baihatuserdanghecamxuc(@Param("user_id") int userid,@Param("music_id") int musicid,@Param("emotion_id") int emotionid);

    @Modifying
    @Query(value = "Insert into recommended_music(user_id,music_id,total_view,emotion_id) values(:user_id,:music_id,1,:emotion_id)",nativeQuery = true)
    public int themcamxucuser(@Param("user_id") int userid,@Param("music_id") int musicid,@Param("emotion_id") int emotionid);

    @Modifying
    @Query(value = "UPDATE recommended_music SET total_view=total_view+1 where user_id =:user_id and music_id =:music_id and emotion_id=:emotion_id",nativeQuery = true)
    public int capnhatcamxucuser(@Param("user_id") int userid,@Param("music_id") int musicid,@Param("emotion_id") int emotionid);

    @Modifying
    @Query(value = "UPDATE recommended_music SET total_view=total_view-1 where user_id is null and music_id =:music_id and emotion_id is null",nativeQuery = true)
    public int truluotnghe(@Param("music_id") int musicid);

    @Query(value = "select * from recommended_music where user_id is null and music_id =:music_id and emotion_id=:emotion_id",nativeQuery = true)
    public RecommendedMusic baihatdanghecamxuc(@Param("music_id") int musicid,@Param("emotion_id") int emotionid);

    @Modifying
    @Query(value = "Insert into recommended_music(music_id,total_view,emotion_id) values(:music_id,1,:emotion_id)",nativeQuery = true)
    public int themcamxuc(@Param("music_id") int musicid,@Param("emotion_id") int emotionid);

    @Modifying
    @Query(value = "UPDATE recommended_music SET total_view=total_view+1 where user_id is null and music_id =:music_id and emotion_id=:emotion_id",nativeQuery = true)
    public int capnhatcamxuc(@Param("music_id") int musicid,@Param("emotion_id") int emotionid);

}
