package cn.zzx.mapper;

import cn.zzx.common.pojo.User;

public interface UserMapper {
    int UserNameCount(String studentId);

    void insertUser(User user);


    User selectUser(User user);
}
