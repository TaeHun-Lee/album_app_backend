package com.teuno.spring.mappers;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.teuno.spring.user.dto.UserDTO;

public interface IUserMapper {
	@Select("select EXISTS (select * from USER where userName=#{userName})")
	Integer checkUserInstance(@Param("userName") String userName);
	@Insert("INSERT INTO USER(userName, userPw, userEmail, userSignedDate) VALUES(#{userName}, #{userPw}, #{userEmail}, #{userSignedDate})")
	Integer insertUser(@Param("userName") String userName, @Param("userPw") String userPw, @Param("userEmail") String userEmail, @Param("userSignedDate") Date userSignedDate);
	@Update("UPDATE USER SET userName=#{userName}, userPw=#{userPw}, userEmail=#{userEmail}, userSignedDate=#{userSignedDate} WHERE userId=#{userId}")
	Integer modifyUser(@Param("userName") String userName, @Param("userPw") String userPw, @Param("userEmail") String userEmail, @Param("userSignedDate") Date userSignedDate, @Param("userId") Integer userId);
	@Delete("DELETE FROM USER WHERE userName=#{userName} AND userPW=#{userPw}")
	Integer deleteUser(@Param("userName") String userName, @Param("userPw") String userPw);
	@Select("SELECT * FROM USER WHERE userName=#{userName}")
	UserDTO selectUser(@Param("userName") String userName);
	@Select("SELECT * FROM USER")
	List<UserDTO> selectUsersForAdmin();
}

