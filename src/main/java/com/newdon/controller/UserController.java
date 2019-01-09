package com.newdon.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.newdon.base.Result;
import com.newdon.entity.User;
import com.newdon.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

/**
 * @author LeiGang
 * @create 2019/1/8 16:52
 * @description
 **/
@RequestMapping("/newdon/user")
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        byte[] usernameBytes = Base64.decodeBase64(username);
        byte[] passwordBytes = Base64.decodeBase64(password);
        username = new String(usernameBytes);
        password = new String(passwordBytes);
        EntityWrapper<User> wrapper = new EntityWrapper();
        wrapper.eq("username", username);
        User user = this.userService.selectOne(wrapper);
        if (null == user) {
            return Result.build(500, "用户不存在!", null);
        }
        if (!StringUtils.equals(user.getPassword(), DigestUtils.md5Hex(password))) {
            return Result.build(500, "密码错误!", null);
        }
        request.getSession().setAttribute("username", username);
        request.getSession().setAttribute("name", user.getName());
        return Result.build(200, "OK", username);
    }

    @GetMapping(value = "/getCurrentUser")
    public Result getCurrentUser(HttpServletRequest httpServletRequest){
        System.out.println(httpServletRequest.getSession().getAttribute("username"));
        if(null == httpServletRequest.getSession().getAttribute("username")){
            return Result.build(400,"OK",0 ,false);
        }
        return Result.build(200,"OK",httpServletRequest.getSession().getAttribute("username"));
    }

    @GetMapping(value = "/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("name");
        return Result.build(200, "账户失效！请重新登录！", 0,false);
    }
}