package cn.zzx.mapper;

import cn.zzx.common.pojo.Question;

import java.util.List;

public interface IndexMapper {
    List<Question> selectSingleQuestion();

    List<Question> selectManyQuestion();

    List<Question> selectJudgeQuestion();
}
