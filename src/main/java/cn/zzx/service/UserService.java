package cn.zzx.service;

import cn.zzx.common.pojo.User;
import cn.zzx.common.utils.MD5Util;
import cn.zzx.common.utils.MapperUtil;
import cn.zzx.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

@Service
public class UserService {
    @Autowired
    private UserMapper um;
    @Autowired
    Jedis cluster;
    //判断用户名是否存在
    public boolean userNameExists(String studentId) {
        int i = um.UserNameCount(studentId);
        return i == 1;
    }
    //注册提交
    public void addUser(User user) {
        String md5Pass = MD5Util.md5(user.getUserPassword());
        user.setUserPassword(md5Pass);
        user.setUserType(1);
        user.setUserAdmin(0);
        um.insertUser(user);
    }

    //登录
    public String dologin(User user) {
        user.setUserPassword(MD5Util.md5(user.getUserPassword()));
        System.out.println(user.toString());
        User u = um.selectUser(user);
        if (u == null) {
            throw new RuntimeException("用户名错误");
        }
        try {
            String Json = MapperUtil.MP.writeValueAsString(u);
            String key = "User" + u.getStudentId();
            cluster.setex(key, 2 * 60 * 60, Json);
            return key;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    //获取登录状态
    public User queryUserJson(String ticket) {
        String studentId = ticket.substring(4);
        try {
            Long pttl = cluster.pttl(ticket);
            if (pttl < 1000 * 60 * 20) {
                cluster.pexpire(ticket, pttl + 60 * 60 * 2);
            }
            String str=cluster.get(ticket);
            User user=MapperUtil.MP.readValue(str,User.class);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //注销
    public void dologout(String ticket,User user) {
     try{
         cluster.del("User"+user.getStudentId());
     }catch (Exception e){
         e.printStackTrace();
     }
    }
}
