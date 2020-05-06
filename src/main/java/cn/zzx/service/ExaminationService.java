package cn.zzx.service;

import cn.zzx.common.pojo.Programme;
import cn.zzx.common.pojo.Question;
import cn.zzx.common.utils.MapperUtil;
import cn.zzx.mapper.ExaminationMapper;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;


import java.io.IOException;
import java.util.*;

@Service
public class ExaminationService {
    private Random random = new Random();

    @Autowired
    private ExaminationMapper em;
    @Autowired
    Jedis jedis;


    /**
     * 随机出考试题
     *
     * @return
     */
    public Object selectQuestion(String studentId) throws Exception {
        //最终返回的题
        List<Question> questions = new ArrayList<>();
        List<String> answer = new ArrayList<>();
        //所以抽随机抽取的id
        Set<Integer> idSetAllSingle = new HashSet<>();
        //临时存id
        List<Integer> idListSingle;
        //所以抽随机抽取的id
        Set<Integer> idSetAllMany = new HashSet<>();
        //临时存id
        List<Integer> idListMany;
        //所以抽随机抽取的id
        Set<Integer> idSetAllJudge = new HashSet<>();
        //临时存id
        List<Integer> idListJudge;

        //先去jedis查数据如果没有再去数据库
        String questionKey = "question_" + studentId;
        String answerKey = "answer_" + studentId;

        if (jedis.exists(questionKey)) {
            String Json = jedis.get(questionKey);

            Object objects = MapperUtil.MP.readValue(Json, Object[].class);
            return objects;
        }

        ///抽题逻辑---------------------------------
        //查询出题方案
        List<Programme> programmeList = em.selectAllProgramme();
        for (int i = 0; i < programmeList.size(); i++) {
            //单元
            int unit = programmeList.get(i).getUnit();
            //单选抽题数
            int singleCount = programmeList.get(i).getSingleCount();
            //多选出题数
            int manyCount = programmeList.get(i).getManyCount();
            //判断题数
            int judgeCount = programmeList.get(i).getJudgeCount();

            //根据单元返回说要id
            //--------------------------------------------
            idListSingle = em.selectSingleId(unit);
            Set<Integer> SetTmpSingle = new HashSet<>();
            while (SetTmpSingle.size() < singleCount) {
                int id = random.nextInt(idListSingle.size());
                SetTmpSingle.add(idListSingle.get(id));
            }
            idSetAllSingle.addAll(SetTmpSingle);

            //--------------------------------------------
            idListMany = em.selectManyId(unit);
            Set<Integer> SetTmpMany = new HashSet<>();
            while (SetTmpMany.size() < manyCount) {
                int id = random.nextInt(idListMany.size());
                SetTmpMany.add(idListMany.get(id));
            }
            idSetAllMany.addAll(SetTmpMany);

            //--------------------------------------------
            idListJudge = em.selectJudgeId(unit);
            Set<Integer> SetTmpJudge = new HashSet<>();
            while (SetTmpJudge.size() < judgeCount) {
                int id = random.nextInt(idListJudge.size());
                SetTmpJudge.add(idListJudge.get(id));
            }
            idSetAllJudge.addAll(SetTmpJudge);
        }
        //单选
        List<Question> singleList = em.selectByIdSingle(idSetAllSingle);
        List<String> singleAnswer = em.selectByIdSingleAnswer(idSetAllSingle);

        //多选
        List<Question> manyList = em.selectByIdMany(idSetAllMany);
        List<String> manyAnswer = em.selectByIdManyAnswer(idSetAllMany);

        //判断
        List<Question> JudgeList = em.selectByIdJudge(idSetAllJudge);
        List<String> JudgeAnswer = em.selectByIdJudgeAnswer(idSetAllJudge);
        answer.addAll(singleAnswer);
        answer.addAll(manyAnswer);
        answer.addAll(JudgeAnswer);
        System.out.println("1" + answer);
        String answerString = MapperUtil.MP.writeValueAsString(answer);
        jedis.set(answerKey, answerString);
        jedis.psetex(answerKey, (int) (1000 * 60 * 60 * 1.5), answerString);
        questions.addAll(singleList);
        questions.addAll(manyList);
        questions.addAll(JudgeList);
        String questionString = MapperUtil.MP.writeValueAsString(questions);
        //jedis.set(questionKey, questionString);
        jedis.psetex(questionKey, (int) (1000 * 60 * 60 * 1.5), questionString);
        return questions;
    }

    public int gradeExams(String studentId, String ansStr) throws IOException {

        String[] split = ansStr.split(",");
        int answer = 0;
        String answerKey = "answer_" + studentId;
        String questionKey = "question_" + studentId;
        if (jedis.exists(answerKey)) {
            String s = jedis.get(answerKey);
            String s1 = jedis.get(questionKey);
            List<Question> readValue = JSON.parseArray(s1, Question.class);
            System.out.println("***" + s1);
            String[] ans = s.substring(2, s.length() - 2).split("\",\"");
            for (int i = 0; i < 40; i++) {
                if ((ans[i].charAt(0) + "").equals(split[i])) {
                    answer += 1;
                } else {
                    em.insertCTB(readValue.get(i), studentId);
                }
            }
            for (int j = 40; j < 60; j++) {
                if ((ans[j].split(" ")[0]).equals(split[j])) {
                    answer += 2;

                } else {
                    em.insertCTB(readValue.get(j), studentId);
                }
            }
            for (int k = 60; k < 70; k++) {
                if ((ans[k].charAt(0) + "").equals(split[k])) {
                    answer += 2;

                } else {
                    em.insertCTB(readValue.get(k), studentId);
                }
            }

        }
        jedis.del(answerKey);
        jedis.del(questionKey);
        return answer;
    }

    public Long timeOut(String studentId) {
        String questionKey = "question_" + studentId;
        Long ttl = jedis.ttl(questionKey);
        return ttl;
    }
}
