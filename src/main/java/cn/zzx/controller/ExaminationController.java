package cn.zzx.controller;

import cn.zzx.common.pojo.Question;
import cn.zzx.common.pojo.SysResult;
import cn.zzx.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("examination")
public class ExaminationController {
    @Autowired
    public ExaminationService es;

    @RequestMapping("all")
    public SysResult questionAll(String studentId){
        try {
            Object questionList = es.selectQuestion(studentId);
            return SysResult.build(200,"success",questionList);
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.build(201,"error",null);
        }
    }

    @RequestMapping("/answer")
    public SysResult gradeExams(String studentId,String ansStr){
       // System.out.println("ss"+ansStr);
        try {
            int fraction = es.gradeExams(studentId,ansStr);
            return SysResult.build(200,"success",fraction);
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.build(201,"error",null);
        }
    }
     @RequestMapping("/timeOut")
    public SysResult timeOut(String studentId){
         try {
             long time = es.timeOut(studentId);
             return SysResult.build(200,"success",time);
         }catch (Exception e){
             e.printStackTrace();
             return SysResult.build(201,"error",null);
         }
     }

}
