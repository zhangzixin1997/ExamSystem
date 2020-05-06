package cn.zzx.mapper;

import cn.zzx.common.pojo.Question;

import java.util.List;

public interface SingleMapper {
    void insertData(Question question);


    List<Question> selectAllData();

    int countAllQuestion();

    void insertChapter(int unit);
}
