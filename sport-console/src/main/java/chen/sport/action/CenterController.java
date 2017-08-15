package chen.sport.action;

import chen.sport.core.pojo.TestTb;
import chen.sport.service.TestTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 16:47 2017/8/13
 * @Modified by:
 */
@Controller
public class CenterController {
    @Autowired
    private TestTbService testTbService;
    @RequestMapping(value = "/test/index.do")
    public String index(Model model) {

        // Dubbo调用测试
        TestTb testTb = new TestTb();
        testTb.setName("范冰冰1111");
        testTb.setBirthday(new Date());
        testTbService.add(testTb);


        return "index";
    }
    @RequestMapping(value = "console/{pageName}.do")
    public String consoleShow(@PathVariable(value = "pageName") String pageName) {
        return pageName;
    }
    // 框架页面
    @RequestMapping(value = "console/frame/{pageName}.do")
    public String consoleFrameShow(@PathVariable(value = "pageName") String pageName) {
        System.out.println("frame");
        return "/frame/" + pageName;
    }

    // 商品
    @RequestMapping(value = "console/product/{pageName}.do")
    public String consoleProductShow(@PathVariable(value = "pageName") String pageName) {
        return "/product/" + pageName;
    }


    // 种类
    @RequestMapping(value = "console/type/{pageName}.do")
    public String consoleTypeShow(@PathVariable(value = "pageName") String pageName) {
        return "/type/" + pageName;
    }


}
