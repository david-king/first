package com.durian.first.api.service.impl;

import com.durian.first.entity.UserEntity;
import com.durian.first.api.dao.UserMapper;
import com.durian.first.api.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author king
 * @since 2019-06-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

}
