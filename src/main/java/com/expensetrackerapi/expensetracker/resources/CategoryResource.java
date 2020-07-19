package com.expensetrackerapi.expensetracker.resources;

import com.expensetrackerapi.expensetracker.domain.Category;
import com.expensetrackerapi.expensetracker.exceptions.EtAuthException;
import com.expensetrackerapi.expensetracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public String getAllCategories(HttpServletRequest httpRequest){
        Integer userId = (Integer) httpRequest.getAttribute("userId");
        if(null != userId)
        return "Authenticated userID: " + userId;
        else
            throw new EtAuthException("User id not present");
    }

    @PostMapping("")
    public ResponseEntity<Category> addCategory(HttpServletRequest httpRequest,
                                                @RequestBody Map<String, Object> categoryMap){
        int userId = (Integer) httpRequest.getAttribute("userId");
        String title = (String) categoryMap.get("title");
        String description = (String) categoryMap.get("description");
        Category category = categoryService.addCategory(userId, title, description);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

}
