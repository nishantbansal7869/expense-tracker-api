package com.expensetrackerapi.expensetracker.resources;

import com.expensetrackerapi.expensetracker.exceptions.EtAuthException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

    @GetMapping("")
    public String getAllCategories(HttpServletRequest httpRequest){
        Integer userId = (Integer) httpRequest.getAttribute("userId");
        if(null != userId)
        return "Authenticated userID: " + userId;
        else
            throw new EtAuthException("User id not present");
    }

}
