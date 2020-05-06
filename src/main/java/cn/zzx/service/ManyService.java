package cn.zzx.service;

import cn.zzx.common.pojo.Question;
import cn.zzx.common.utils.UUIDUtil;

import cn.zzx.mapper.ManyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import java.util.List;
import java.util.Scanner;
@Service
public class ManyService {
    @Autowired
    ManyMapper sm;

    public void insertData(MultipartFile many) {

        String originalFilename = many.getOriginalFilename();
        try {
            String substringName = originalFilename.substring(originalFilename.lastIndexOf("."));
            if (!substringName.matches(".(txt)$")) {
                throw new RuntimeException();
            }
            String dir = "d://question/" + UUIDUtil.getUUID() + ".txt";
            many.transferTo(new File(dir));

            int len = -1;
            FileInputStream fileInputStream = new FileInputStream(new File(dir));
            Scanner scanner = new Scanner(fileInputStream);
            Question question = new Question();
            int unit = Integer.parseInt(scanner.nextLine());
            question.setUnit(unit);
            sm.insertChapter(unit);


            int count=0;
            while (scanner.hasNext()) {

                question.setTitle(scanner.nextLine());
                question.setA(scanner.nextLine());
                question.setB(scanner.nextLine());
                question.setC(scanner.nextLine());
                question.setD(scanner.nextLine());
                question.setAnswer(scanner.nextLine());
                try {
                    sm.insertData(question);
                }catch (Exception e){
                    count++;
                    e.printStackTrace();

                }


            }

            if (count!=0){
                throw new RuntimeException("有"+count+"道重复的题已经合并");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Question> selectAllData() {

        List<Question> questions= sm.selectAllData();
        if (questions==null){
            throw new RuntimeException("题库没数据");

        }
        return questions;

    }


    public int countAllQuestion() {
        int count=sm.countAllQuestion();
        if (count==0){
            throw new RuntimeException("题库为空");
        }
        return count;
    }
}
