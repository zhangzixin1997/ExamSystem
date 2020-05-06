package cn.zzx.controller;

import cn.zzx.common.pojo.Question;
import cn.zzx.common.pojo.SysResult;
import cn.zzx.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("search")
public class IndexController {
    @Autowired
    private IndexService is;

    //访问接口创建索引
    @RequestMapping("init/{indexName}")
    public SysResult createIndex(@PathVariable String indexName) {
        try {
            is.createIndex(indexName);
            return SysResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "", null);
        }
    }

    @RequestMapping("query")
    public List<Question> query(String query) {
        return is.query(query);
    }
}