package pers.allen.whu_java_terminal.service;

import pers.allen.whu_java_terminal.model.entity.User;

import java.util.Map;

public interface UserService {
    /**
     * 新增用户
     * @param userInfo
     * @return
     */
    int save(Map<String, String> userInfo);


    String findByPhoneAndPwd(String phone, String pwd);

    User findByUserId(Integer userId);
}
