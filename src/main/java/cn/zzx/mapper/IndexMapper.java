package cn.zzx.mapper;

import cn.zzx.common.pojo.Question;

import java.util.List;

public interface IndexMapper {
    List<Question> selectSingleQuestion();

    List<Question> selectManyQuestion();

    List<Question> selectJudgeQuestion();

    List<Question> query1(String query);

    List<Question> query2(String s);

    List<Question> query3(String s);
}
