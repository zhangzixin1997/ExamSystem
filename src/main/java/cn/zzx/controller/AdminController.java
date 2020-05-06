package cn.zzx.controller;

import cn.zzx.common.pojo.SysResult;
import cn.zzx.common.pojo.User;
import cn.zzx.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService as;

    //获取所有id信息
    @RequestMapping("/all")
    public SysResult selectAllUserData() {
        try {
            List<User> userList = as.selectAllUserData();
            return SysResult.build(200, "", userList);
        } catch (Exception e) {
            return SysResult.build(201, "error", null);
        }
    }

    //删除用户
    @RequestMapping("/delete")
    public SysResult deleteByStudentId(String studentId) {

        try {
            as.deleteByStudentId(studentId);
            return SysResult.build(200, "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "error", null);
        }
    }

    @RequestMapping("/update")
    public SysResult updateByStudentId(String studentId) {
        try {
            as.updateByStudentId(studentId);
            return SysResult.build(200, "更新成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, e.getMessage(), null);
        }
    }

    @RequestMapping("/clear")
    public SysResult clearUser() {
        try {
            as.clearUser();
            return SysResult.build(200, "清除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "error", null);
        }
    }

    @RequestMapping("/deleteSingle")
    public SysResult deleteSingle(String id) {

        try {
            as.deleteSingle(id);
            return SysResult.build(200, "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "error", null);
        }
    }

    @RequestMapping("/deleteMany")
    public SysResult deleteMany(String id) {

        try {
            as.deleteMany(id);
            return SysResult.build(200, "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "error", null);
        }
    }

    @RequestMapping("/deleteJudge")
    public SysResult deleteJudge(String id) {

        try {
            as.deleteJudge(id);
            return SysResult.build(200, "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "error", null);
        }
    }

    @RequestMapping("/jurisdiction")
    public SysResult jurisdictionSee(String studentId) {
        System.out.println(studentId);

        try {
            as.jurisdictionSee(studentId);
            return SysResult.build(200, "成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "权限不够", null);
        }
    }

    @RequestMapping("/weight")
    public SysResult insertWeight(MultipartFile qu) {
        try {
            as.insertWeight(qu);
            return SysResult.build(200, "成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "格式有问题", null);
        }
    }
}
