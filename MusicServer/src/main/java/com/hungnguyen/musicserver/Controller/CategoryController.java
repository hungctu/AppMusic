package com.hungnguyen.musicserver.Controller;

import com.hungnguyen.musicserver.Entity.Category;
import com.hungnguyen.musicserver.Repository.CategoryRepository;
import com.hungnguyen.musicserver.Services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/server")
public class CategoryController {

    @Autowired
    private CategoryServices categorys;

    @GetMapping("/randomcategory")
    public List<Category> getrandom(){
        return categorys.randomcategory();
    }

    @GetMapping("/allcategory")
    public List<Category> getallca(){
        return categorys.findAll();
    }
}
