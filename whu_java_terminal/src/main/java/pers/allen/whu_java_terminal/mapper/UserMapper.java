package pers.allen.whu_java_terminal.mapper;

import org.apache.ibatis.annotations.Param;
import pers.allen.whu_java_terminal.model.entity.User;

public interface UserMapper {
    int save(User user);

    User findByPhone(@Param("phone") String phone);


    User findByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String pwd);

    User findByUserId(@Param("user_id") Integer userId);
}
