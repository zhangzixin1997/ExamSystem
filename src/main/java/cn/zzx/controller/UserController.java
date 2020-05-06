package cn.zzx.controller;

import cn.zzx.common.pojo.SysResult;
import cn.zzx.common.pojo.User;
import cn.zzx.common.utils.CookieUtils;
import cn.zzx.service.UserService;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService us;

    //注册功能
    //校验用户名是否存在
    @RequestMapping("check")
    public SysResult checkUserName(String studentId) {
        if (us.userNameExists(studentId)) {
            return SysResult.build(201, "用户名已存在", null);
        } else {
            return SysResult.ok();
        }

    }

    //注册提交
    @RequestMapping("save")
    public SysResult addUser(@Valid User user, Errors errors) {
        if (errors.hasErrors()){
            return new SysResult(201,errors.getFieldError().getDefaultMessage(),null);
        }
        try {
            us.addUser(user);
            return SysResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "新增失败", null);
        }
    }

    //登录
    @RequestMapping("login")
    public SysResult login(User user, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(user.toString());
        try {
            String ticket = us.dologin(user);

            if (StringUtils.isNotEmpty(ticket)) {
                CookieUtils.setCookie(request, response, "EM_TICKET", ticket);
                return SysResult.ok();
            }
            return SysResult.build(201, "error", null);
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "错误", null);

        }

    }

    //获取用户登录状态
    @RequestMapping("query")
    public SysResult query(String ticket) {
        if (StringUtils.isEmpty(ticket)) {
            return SysResult.build(201, "", null);
        }
        User user = us.queryUserJson(ticket);
        if (user == null) {
            return SysResult.build(201, "登录超时", null);
        } else {
            return SysResult.build(200, "登录成功", user);
        }
    }

    //注销
    @RequestMapping("logout")
    public SysResult logout(HttpServletRequest request, HttpServletResponse response, User user) {
        String ticket = CookieUtils.getCookieValue(request, "EM_TICKET");
        try {
            us.dologout(ticket, user);
            CookieUtils.deleteCookie(request, response, "EM_TICKET");
            return SysResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "error", null);
        }

    }
}
