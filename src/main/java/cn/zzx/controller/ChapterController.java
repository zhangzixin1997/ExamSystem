package cn.zzx.controller;

import cn.zzx.common.pojo.Chapter;
import cn.zzx.common.pojo.Question;
import cn.zzx.common.pojo.SysResult;
import cn.zzx.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    ChapterService cs;

    @RequestMapping("all")
    public SysResult selectAllData() {

        try {

            List<Chapter> chapterLsit = cs.selectAllData();
            return SysResult.build(200, "", chapterLsit);
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "error", null);
        }
    }

    @RequestMapping("type")
    public SysResult selectByUnit(String unit) {
        try {
            List<Question> questionList = cs.selectByUnit(unit);
            if (questionList.size() == 0) {
                return SysResult.build(201, "题库没有数据", null);
            }
            return SysResult.build(200, "", questionList);
        } catch (Exception e) {
            return SysResult.build(201, "题库没有数据", null);

        }
    }

    @RequestMapping("count")
    public SysResult countByUnit(String unit) {
        try {
            int count = cs.countByUnit(unit);
            if (count==0){
                return SysResult.build(201, "题库没有数据", null);
            }

            return SysResult.build(200, "", count);
        } catch (Exception e) {
            return SysResult.build(201, "题库没有数据", null);


        }
    }
}
