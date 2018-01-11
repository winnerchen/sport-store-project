package chen.sport.core.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Yiheng Chen
 * @Description: 登录控制器
 * @Date: Created in 15:20 2017/8/27
 * @Modified by:
 */
@Controller
public class LoginAction {
    //显示登录页面
    @RequestMapping(value = "/login.aspx")
    public String showLogin() {
        return "login";
    }
}
