package com.Wang.dao.Impl;

import com.Wang.dao.RoleDao;
import com.Wang.domain.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Role> findAll() {
        String sql = "select * from sys_role";
        return template.query(sql,new BeanPropertyRowMapper<Role>(Role.class));
    }

    @Override
    public void save(Role role) {
        String sql ="insert into sys_role values(null,?,?)";
        template.update(sql,role.getRoleName(),role.getRoleDesc());
    }

    @Override
    public List<Role> findById(Long id) {
        String sql = "select * from sys_user_role ur,sys_role r where ur.roleid=r.id and ur.userid=?";
        return template.query(sql,new BeanPropertyRowMapper<Role>(Role.class),id);
    }

    @Override
    public void delUserRoleRel(Long roleId) {
        String sql = "delete from sys_user_role where roleId = ?";
        template.update(sql,roleId);
    }

    @Override
    public void del(Long roleId) {
        String sql = "delete from sys_role where id = ?";
        template.update(sql,roleId);
    }
}
