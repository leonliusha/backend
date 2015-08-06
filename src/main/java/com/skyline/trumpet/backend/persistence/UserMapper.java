package com.skyline.trumpet.backend.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import com.skyline.trumpet.backend.model.User;

public interface UserMapper {
	@Options(useGeneratedKeys=true,keyProperty="id", keyColumn="id")
	@Insert("insert into user (user_name,password,email,phone) VALUES (#{userName},#{password},#{email},#{phone})")
	void userRegister(User user);
	
	@Results(value={
			@Result(column="user_name", property="userName")})
	@Select("select * from user where user_name = #{userName} and password = #{password}")
	List<User> userLogin(User user);
	
	
	@Select("select * from user where id = #{userId}")
	List<User> getUserById(@Param("userid") long id);
}
