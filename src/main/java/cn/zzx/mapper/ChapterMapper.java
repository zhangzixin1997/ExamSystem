package cn.zzx.mapper;

import cn.zzx.common.pojo.Chapter;
import cn.zzx.common.pojo.Question;

import java.util.List;

public interface ChapterMapper {
    List<Chapter> selectAllData();

    List<Question> selectSingleByUnit(int c);

    List<Question> selectManyByUnit(int c);

    List<Question> selectJudgeByUnit(int c);

    int countSingleByUnit(int c);

    int countManyByUnit(int c);

    int countJudgeByUnit(int c);
}
