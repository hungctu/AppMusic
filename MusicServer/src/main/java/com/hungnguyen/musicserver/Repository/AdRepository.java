package com.hungnguyen.musicserver.Repository;

import com.hungnguyen.musicserver.Entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Advertisement,Integer> {
}
