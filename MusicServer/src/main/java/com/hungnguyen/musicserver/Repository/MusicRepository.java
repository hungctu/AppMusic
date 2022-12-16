package com.hungnguyen.musicserver.Repository;

import com.hungnguyen.musicserver.Entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music,Integer> {

    @Query(value = "select * from Music ORDER BY rand(\"date(Ymd)\") LIMIT 10",nativeQuery = true)
    public List<Music> getrandom();
}
