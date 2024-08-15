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

    @Select("SELECT * FROM users WHERE role = #{role}")
    List<User> findByRole(@Param("role") String role);

    @Insert("INSERT INTO users(username, password, email, firstName, lastName, location, role) VALUES(#{username}, #{password}, #{email},#{firstName}, #{lastName}, #{role}, #{location})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Update("UPDATE users SET username=#{username}, password=#{password}, email=#{email}, location=#{location}, firstName=#{firstName}, lastName=#{lastName}, role=#{role} WHERE id=#{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    void delete(Long id);
}
