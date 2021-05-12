package com.Wang.dao.Impl;

import com.Wang.dao.UserDao;
import com.Wang.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    @Override
    public List<User> findAll() {
        String sql = "select * from sys_user";
        return template.query(sql,new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public Long save(User user) {
        //jdbc 返回当前创建用户id
        String sql = "insert into sys_user value(?,?,?,?,?)";
//        创建PreparedStatementCreator
        PreparedStatementCreator creator=new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                //使用原始JDBC完成的PreparedStatement的组键
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);//返回生成主键
                preparedStatement.setObject(1,null);
                preparedStatement.setString(2,user.getUsername());
                preparedStatement.setString(3,user.getEmail());
                preparedStatement.setString(4,user.getPassword());
                preparedStatement.setString(5,user.getPhoneNum());
                return preparedStatement;
            }
        };
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(creator,keyHolder);
        /*
        template.update(sql,null,user.getUsername(),user.getEmail(),user.getPassword(),user.getPhoneNum());*/
        return keyHolder.getKey().longValue();//返回当前用户ID 该ID是数据库自动生成的
    }
    @Override
    public void saveRoleRel(Long id, Long[] roleIds) {
        String sql ="insert into sys_user_role value(?,?)";
        for (Long roleId : roleIds) {
            template.update(sql,id,roleId);
        }
    }

    @Override
    public void delUserRoleRel(Long userId) {
        String sql = "delete from sys_user_role where userId = ?";
        template.update(sql,userId);
    }

    @Override
    public void del(Long userId) {
        String sql="delete from sys_user where id = ?";
        template.update(sql,userId);
    }
}
