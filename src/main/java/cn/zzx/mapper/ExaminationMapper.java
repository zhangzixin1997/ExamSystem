package cn.zzx.mapper;

import cn.zzx.common.pojo.Programme;
import cn.zzx.common.pojo.Question;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Set;

public interface ExaminationMapper {
    List<Programme> selectAllProgramme();
    List<Integer> selectSingleId(Integer unit);
    List<Integer> selectManyId(Integer unit);
    List<Integer> selectJudgeId(Integer unit);

    List<Question> selectByIdSingle(@Param("ids") Set<Integer> ids);

    List<Question> selectByIdMany(@Param("idSetAllMany") Set<Integer> idSetAllMany);

    List<Question> selectByIdJudge(@Param("idSetAllJudge") Set<Integer> idSetAllJudge);

    List<String> selectByIdSingleAnswer(@Param("selectByIdSingleAnswer")Set<Integer> idSetAllSingle);
    List<String> selectByIdManyAnswer(@Param("selectByIdManyAnswer")Set<Integer> idSetAllMany);

    List<String> selectByIdJudgeAnswer(@Param("selectByIdJudgeAnswer")Set<Integer> idSetAllJudge);

    void insertCTB(@Param("question") Question question,@Param("studentId") String studentId);
}
