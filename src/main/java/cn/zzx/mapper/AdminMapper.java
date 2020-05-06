package cn.zzx.mapper;

import cn.zzx.common.pojo.Programme;
import cn.zzx.common.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    List<User> selectAllUserData();

    void deleteByStudent(String studentId);

    void updateByStudentId(String studentId);

    int selectByData(String studentId);

    void deleteUser();

    void deleteSingle(String id);

    void deleteMany(String id);

    void deleteJudge(String id);

    int jurisdictionSee(String studentId);


    void insertWeight(Programme programme);

    void deleteData();
}
