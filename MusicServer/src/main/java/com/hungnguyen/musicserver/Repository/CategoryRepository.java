package com.hungnguyen.musicserver.Repository;

import com.hungnguyen.musicserver.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    @Query(value = "SELECT DISTINCT * FROM Category c ORDER BY rand(\"date(Ymd)\") LIMIT 5",nativeQuery = true)
    public List<Category> randomcategory();
}
