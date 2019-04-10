package com.shipping.dao;

import com.shipping.pojo.User;
import org.springframework.data.repository.query.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String userName);

    int checkEmail(String email);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    String selectQuestionByUsername(String username);

    int checkAnswer(@org.apache.ibatis.annotations.Param("username")String username, @org.apache.ibatis.annotations.Param("question")String question, @org.apache.ibatis.annotations.Param("answer")String answer);

    int updatePasswordByUsername(@org.apache.ibatis.annotations.Param("username")String username, @org.apache.ibatis.annotations.Param("passwordNew")String passwordNew);

    int checkPassword(@org.apache.ibatis.annotations.Param(value="password")String password, @org.apache.ibatis.annotations.Param("userId")Integer userId);

    int checkEmailByUserId(@org.apache.ibatis.annotations.Param(value="email")String email, @org.apache.ibatis.annotations.Param(value="userId")Integer userId);
}
