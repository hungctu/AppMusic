package com.hungnguyen.musicserver.Repository;

import com.hungnguyen.musicserver.Entity.MusicList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MusicListRepository extends JpaRepository<MusicList,Integer> {

    @Query(value = "SELECT * FROM MusicList where user_id=:user_id ",nativeQuery = true)
    public List<MusicList> getmusiclist(@Param("user_id") int id);

    @Query(value = "SELECT * FROM MusicList where user_id=:user_id and music_list_name='Bài hát yêu thích'",nativeQuery = true)
    public List<MusicList> getmusiclistyeuthichtheoid(@Param("user_id") int id);

    @Query(value = "SELECT * FROM MusicList where user_id=:user_id and " +
            "music_list_name=:name ORDER BY music_list_date_create DESC LIMIT 1",nativeQuery = true)
    public List<MusicList> getmusiclisttheoten(@Param("user_id") int id,@Param("name") String name);
}
