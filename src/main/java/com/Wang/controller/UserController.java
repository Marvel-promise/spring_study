package com.Wang.controller;

import com.Wang.domain.Role;
import com.Wang.domain.User;
import com.Wang.service.RoleService;
import com.Wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public ModelAndView list(){
        List<User> userList = userService.list();
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }
    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("user-add");
        return modelAndView;
    }
    @RequestMapping("/save")
    public String save(User user, Long[] roleIds){
        ModelAndView modelAndView = new ModelAndView();
        userService.save(user,roleIds);
        return "redirect:/user/list";
    }
    @RequestMapping("/del/{userId}")
    public String del(@PathVariable("userId") Long userId){
        ModelAndView modelAndView = new ModelAndView();
        userService.del(userId);
        return "redirect:/user/list";
    }
}
