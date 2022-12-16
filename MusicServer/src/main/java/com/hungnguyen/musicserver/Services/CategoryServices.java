package com.hungnguyen.musicserver.Services;

import com.hungnguyen.musicserver.Entity.Category;
import com.hungnguyen.musicserver.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServices {

    @Autowired
    private CategoryRepository categorys;

    public List<Category> findAll(){
        return categorys.findAll();
    }

    public List<Category> randomcategory(){
        return categorys.randomcategory();
    }
}
