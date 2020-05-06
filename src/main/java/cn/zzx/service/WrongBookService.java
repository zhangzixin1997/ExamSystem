package cn.zzx.service;

import cn.zzx.common.pojo.Question;
import cn.zzx.mapper.WrongBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WrongBookService {
    @Autowired
    private WrongBookMapper wbm;

    public List<Question> selectAllData(String studentId) {

        return wbm.selectAllBystudentId(studentId);
    }

    public void deleteById(int id) {
        wbm.deleteById(id);
    }
}
