package com.Wang.service;

import com.Wang.domain.Role;

import java.util.List;

public interface RoleService {

    public List<Role> list();

    void save(Role role);

    void del(Long roleId);
}
