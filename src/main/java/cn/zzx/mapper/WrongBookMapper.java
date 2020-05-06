package cn.zzx.mapper;

import cn.zzx.common.pojo.Question;

import java.util.List;

public interface WrongBookMapper {
    List<Question> selectAllBystudentId(String studentId);

    void deleteById(int id);


}
