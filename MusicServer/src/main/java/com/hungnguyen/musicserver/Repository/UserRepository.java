package com.hungnguyen.musicserver.Repository;

import com.hungnguyen.musicserver.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "select * from User where username=:usn and password=Md5(:pass) ",nativeQuery = true)
    public List<User> login(@Param("usn") String username, @Param("pass") String password);

    @Query(value = "select * from User where username=:usn ",nativeQuery = true)
    public List<User> kiemtra(@Param("usn") String username);
}
