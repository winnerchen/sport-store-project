package chen.sport.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 16:47 2017/8/13
 * @Modified by:
 */
@Controller
public class CenterController {
    @RequestMapping(value="/test/index.do")
    public String index(Model model)
    {
        System.out.println("haha");
        return "index";
    }

}
