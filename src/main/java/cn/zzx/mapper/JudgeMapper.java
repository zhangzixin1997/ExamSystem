package cn.zzx.mapper;

import cn.zzx.common.pojo.Question;

import java.util.List;

public interface JudgeMapper {

    List<Question> selectAllData();

    int countAllQuestion();

    void insertData(Question question);
    void insertChapter(int unit);
}
