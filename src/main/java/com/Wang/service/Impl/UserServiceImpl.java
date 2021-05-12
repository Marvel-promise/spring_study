package com.Wang.service.Impl;

import com.Wang.dao.RoleDao;
import com.Wang.dao.UserDao;
import com.Wang.domain.Role;
import com.Wang.domain.User;
import com.Wang.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao;
    public void setDao(UserDao dao) {
        this.dao = dao;
    }
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<User> list() {
        List<User> users = dao.findAll();
        //返回之前将当前角色所具备哪些角色进行封装
        for (User user : users) {
            Long id = user.getId();
//            将id作为参数 查询当前userId的集合数;
          List<Role> roles = roleDao.findById(id);
          user.setRoles(roles);
        }
        return users;
    }

    @Override
    public void save(User user, Long[] roleIds) {
        //第一步， 向sys_user表中存储数据
        Long userId = dao.save(user);
        //向sys_user_role关系表中存多条数据
        dao.saveRoleRel(userId,roleIds);
    }

    @Override
    public void del(Long userId) {
        //1.删除关系表数据
        dao.delUserRoleRel(userId);
        //2.删除user表数据
        dao.del(userId);
    }
}
