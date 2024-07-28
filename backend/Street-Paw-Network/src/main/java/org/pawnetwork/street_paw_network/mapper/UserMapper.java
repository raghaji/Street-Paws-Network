package org.pawnetwork.street_paw_network.mapper;

import org.apache.ibatis.annotations.*;
import org.pawnetwork.street_paw_network.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM users")
    List<User> findAll();

    @Insert("INSERT INTO users(username, password, email) VALUES(#{username}, #{password}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Update("UPDATE users SET username=#{username}, password=#{password}, email=#{email} WHERE id=#{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    void delete(Long id);
}
