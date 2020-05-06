package cn.zzx.common.pojo;

import cn.zzx.common.utils.MapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Questions {
    List<Question> questions;

    public Questions() {
    }

    public Questions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

/*
    public Questions(List<Question> questions) {
        this.setQuestions(questions);
    }
public List<Question> getQuestions() {
        List<Question> qq = new ArrayList<>();
        for (String question:questions){
            try {
                qq.add(MapperUtil.MP.readValue(question,Question.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return qq;

    }

    public void setQuestions(List<Question> questions) {
        List<String> qs = new ArrayList<>();
        for (Question question : questions) {
            try {
                qs.add(MapperUtil.MP.writeValueAsString(question));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        this.questions = qs;
    }*/
}