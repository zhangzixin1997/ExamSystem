package cn.zzx.service;


import cn.zzx.common.pojo.Question;
import cn.zzx.common.utils.MapperUtil;
import cn.zzx.mapper.IndexMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class IndexService {
    @Autowired
    private IndexMapper im;
//    @Autowired
//    private TransportClient client;

    /*public void createIndex(String indexName) throws JsonProcessingException {
        IndicesExistsResponse resp = client.admin().indices().prepareExists(indexName).get();
        if (!resp.isExists()) {
            //不存在创建
            HashMap<String, Object> map = new HashMap<>();
            client.admin().indices().prepareCreate(indexName).get();
        }
        //整理数据源
        List<Question> pList = im.selectSingleQuestion();
        List<Question> pList1 = im.selectManyQuestion();
        List<Question> pList2 = im.selectJudgeQuestion();
        pList.addAll(pList1);
        pList.addAll(pList2);
        //循环pList解析Json
        for (Question p : pList) {
            String pJson = MapperUtil.MP.writeValueAsString(p);
            IndexRequestBuilder req = client.prepareIndex(indexName, "question", p.getTitle());
            req.setSource(pJson).get();
        }
    }

    public List<Question> query(String text) {
        MatchQueryBuilder query = QueryBuilders.matchQuery("title", text);
        SearchRequestBuilder req = client.prepareSearch("queindex");
        SearchResponse resp = req.setQuery(query)*//*.setFrom((page - 1) * rows).setSize(rows)*//*.get();
        SearchHits hits = resp.getHits();
        List<Question> pList = new ArrayList<>();
        for (SearchHit hit : hits) {
            String pJson = hit.getSourceAsString();
            Question question;
            try {
                question = MapperUtil.MP.readValue(pJson, Question.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            pList.add(question);
        }
        return pList;
    }*/

    public List<Question> query1(String query) {
        List<Question> pList = new ArrayList<>();
        List<Question> questions = im.query1("%" + query + "%");
        List<Question> questions1 = im.query2("%" + query + "%");
        List<Question> questions2 = im.query3("%" + query + "%");
        pList.addAll(questions);
        pList.addAll(questions1);
        pList.addAll(questions2);
        return pList;
    }
}