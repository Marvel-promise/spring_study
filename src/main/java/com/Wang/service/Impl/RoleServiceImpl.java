package com.Wang.service.Impl;

import com.Wang.dao.RoleDao;
import com.Wang.domain.Role;
import com.Wang.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> list() {
        List<Role> roleList = roleDao.findAll();
        return roleList;
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);

    }

    @Override
    public void del(Long roleId) {
        roleDao.delUserRoleRel(roleId);
        roleDao.del(roleId);
    }
}
