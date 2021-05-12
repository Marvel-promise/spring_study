package com.Wang.dao;

import com.Wang.domain.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> findAll();

    void save(Role role);

    List<Role> findById(Long id);
    void delUserRoleRel(Long roleId);
    void del(Long roleId);
}
