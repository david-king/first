package com.durian.first.api.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.durian.first.api.service.IUserService;
import com.durian.first.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author king
 * @since 2019-06-29
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public UserEntity getUser(@RequestParam("id") int id) {
        UserEntity userEntity = userService.getById(id);
        return userEntity;
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public IPage getUserList(Page page) {
        IPage iPage = userService.page(page);
        return iPage;
    }

}

