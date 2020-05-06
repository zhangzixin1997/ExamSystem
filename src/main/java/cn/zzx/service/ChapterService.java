package cn.zzx.service;

import cn.zzx.common.pojo.Chapter;
import cn.zzx.common.pojo.Question;
import cn.zzx.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {
    @Autowired
    ChapterMapper cm;
    //返回章节数据
    public List<Chapter> selectAllData() {
        List<Chapter> chapterList = cm.selectAllData();
        if (chapterList == null) {
            throw new RuntimeException("没有章节数据");

        }
        return chapterList;
    }
 //根据单元返回数据
    public List<Question> selectByUnit(String unit) {
        int c = Integer.parseInt(String.valueOf(unit.charAt(0)));

        String type = unit.substring(1);

        if (type.equals("single")) {
            List<Question> single = cm.selectSingleByUnit(c);
            return single;
        } else if (type.equals("many")) {
            List<Question> many = cm.selectManyByUnit(c);
            return many;
        } else if (type.equals("judge")) {
            List<Question> judge = cm.selectJudgeByUnit(c);
            return judge;
        }
        return null;
    }
 //根据单元返回数量
    public int countByUnit(String unit) {
        int c = Integer.parseInt(String.valueOf(unit.charAt(0)));

        String type = unit.substring(1);

        if (type.equals("single")) {
            int single = cm.countSingleByUnit(c);
            return single;
        } else if (type.equals("many")) {
            int many = cm.countManyByUnit(c);
            return many;
        } else if (type.equals("judge")) {
            int judge = cm.countJudgeByUnit(c);
            return judge;
        }
        return 0;

    }
}
