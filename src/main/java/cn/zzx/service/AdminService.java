package cn.zzx.service;

import cn.zzx.common.pojo.Programme;
import cn.zzx.common.pojo.Question;
import cn.zzx.common.pojo.User;
import cn.zzx.common.utils.UUIDUtil;
import cn.zzx.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Service
public class AdminService {
    @Autowired
    AdminMapper am;

    //查询用户数据
    public List<User> selectAllUserData() {
        List<User> userList = am.selectAllUserData();
        if (userList == null) {
            throw new RuntimeException("没有用户数据");
        }
        return userList;
    }

    //删除用户
    public void deleteByStudentId(String studentId) {
        am.deleteByStudent(studentId);
    }

    //修改用户权限
    public void updateByStudentId(String studentId) {
        try {
            if (1 == am.selectByData(studentId)) {
                throw new RuntimeException("已经是管理员");
            }
            am.updateByStudentId(studentId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //清除用户
    public void clearUser() {
        am.deleteUser();
    }

    //删除单选
    public void deleteSingle(String id) {
        am.deleteSingle(id);
    }

    //删除多选
    public void deleteMany(String id) {
        am.deleteMany(id);
    }

    //删除判断
    public void deleteJudge(String id) {
        am.deleteJudge(id);
    }

    //权限判断
    public void jurisdictionSee(String studentId) {
        int admin = am.jurisdictionSee(studentId);
        if (admin == 1) {
            throw new RuntimeException("权限不够");
        }
    }

    public void insertWeight(MultipartFile file) {
        am.deleteData();
        String originalFilename = file.getOriginalFilename();
        try {
            String substringName = originalFilename.substring(originalFilename.lastIndexOf("."));
            if (!substringName.matches(".(txt)$")) {
                throw new RuntimeException();
            }
            String dir = "d://question/" + UUIDUtil.getUUID() + ".txt";
            file.transferTo(new File(dir));
            FileInputStream fileInputStream = new FileInputStream(new File(dir));
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                String[] split = scanner.nextLine().split("/");
                Programme programme = new Programme(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));


                am.insertWeight(programme);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
