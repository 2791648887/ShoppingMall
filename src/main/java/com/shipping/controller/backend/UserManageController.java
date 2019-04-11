package com.shipping.controller.backend;

import com.shipping.common.Const;
import com.shipping.common.ServerResponse;
import com.shipping.pojo.User;
import com.shipping.service.IUserService;
import com.shipping.util.CookieUtil;
import com.shipping.util.JsonUtil;
import com.shipping.util.RedisShardedPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String userName, String password , HttpSession session, HttpServletResponse response) {
        ServerResponse<User> response1 = iUserService.login(userName, password);
        if (response1.inSuccess()) {
            User user = response1.getData();
            if (user.getRole() == Const.Role.ROLE_ADMIN) {
                //说明登录的是管理员账号
//                session.setAttribute(Const.CURRENT_USER, user);
//                   新增redis共享cookie，session的方式
                CookieUtil.writeLoginToken(response, session.getId());
                RedisShardedPoolUtil.setEx(session.getId(), JsonUtil.obj2String(response1.getData()), Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
                return response1;
            } else {
                return ServerResponse.createByErrorMessage("不是管理员,无法登录");
            }
        }
        return response1;
    }
}
