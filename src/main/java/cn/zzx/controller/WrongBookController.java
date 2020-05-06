package cn.zzx.controller;

import cn.zzx.common.pojo.Question;
import cn.zzx.common.pojo.SysResult;
import cn.zzx.service.WrongBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wrongBook")
public class WrongBookController {
    @Autowired
    private WrongBookService ws;
    @RequestMapping("/all")
    public SysResult selectAllData(String studentId){
        try {

            List<Question> questions =ws.selectAllData(studentId);
            return SysResult.build(200,"",questions);
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.build(201,"error",null);
        }
    }
    @RequestMapping("/deleteBoot")
    public SysResult deleteByStudentId(int id) {

        try {
            ws.deleteById(id);
            return SysResult.build(200, "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "error", null);
        }
    }
}
