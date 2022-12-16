package com.hungnguyen.musicserver.Repository;

import com.hungnguyen.musicserver.Entity.Music_MusicList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MMLRepository extends JpaRepository<Music_MusicList,Integer> {

    @Query(value = "select * from music_musiclist where music_id=:music_id and music_list_id=:ml_id",nativeQuery = true)
    public Music_MusicList ktmusic_musiclist(@Param("music_id") int musicid,@Param("ml_id") int id);

    @Modifying
    @Query(value = " DELETE FROM music_musiclist WHERE music_id=:music_id and music_list_id=:ml_id",nativeQuery = true)
    public int Xoabaihatkhoidanhsach(@Param("music_id") int musicid, @Param("ml_id") int id);
}
