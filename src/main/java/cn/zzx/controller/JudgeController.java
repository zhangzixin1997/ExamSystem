package cn.zzx.controller;

import cn.zzx.common.pojo.Question;
import cn.zzx.common.pojo.SysResult;
import cn.zzx.service.JudgeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("/judge")
public class JudgeController {
        @Autowired
        JudgeService ss;
        //添加题
        @RequestMapping("/question")
        public SysResult insertData(MultipartFile judge)  {
            try {
                ss.insertData(judge);
                return SysResult.ok();
            } catch (Exception e) {
                e.printStackTrace();
                return SysResult.build(201, e.getMessage(), null);
            }


        }
        //顺序练习
        @RequestMapping("/all")
        public SysResult selectAllData(){
            try {
                List<Question> questions =ss.selectAllData();
                return SysResult.build(200,"",questions);
            }catch (Exception e){
                e.printStackTrace();
                return SysResult.build(201,"error",null);
            }
        }
        //总题数
        @RequestMapping("/count")
        public SysResult countAllQuestion(){
            try{
                int count=ss.countAllQuestion();
                return SysResult.build(200,"",count);
            }catch (Exception e){
                return SysResult.build(201,"error",null);
            }
        }
    }


