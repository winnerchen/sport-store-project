package chen.sport.action;

import chen.sport.core.pojo.SuperPojo;
import chen.sport.service.SolrService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description: 前台首页控制器
 * @Date: Created in 19:13 2017/8/20
 * @Modified by:
 */
@Controller
public class IndexController {
    @Autowired
    private SolrService solrService;
    //显示前台首页
    @RequestMapping(value="/")
    public String showIndex()
    {
        return "index";
    }

    @RequestMapping("/search")
    public String indexSearch(Model model,String keyword,String sort) throws SolrServerException {

        List<SuperPojo> products = solrService.findProductByKeyWord(keyword,sort);

        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        // 将反转前的sort丢给页面 sort2
        model.addAttribute("sort2", sort);
        // 反转排序规则
        if (sort.equals("price asc")) {
            sort = "price desc";
        } else {
            sort = "price asc";
        }
        model.addAttribute("sort", sort);
        //System.out.println(page.getResult().size());

        return "search";

    }

}
