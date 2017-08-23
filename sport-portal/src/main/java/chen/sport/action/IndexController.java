package chen.sport.action;

import chen.sport.core.pojo.Brand;
import chen.sport.core.pojo.SuperPojo;
import chen.sport.core.tools.PageHelper;
import chen.sport.service.BrandService;
import chen.sport.service.SolrService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.TreeMap;

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
    @Autowired
    private BrandService brandService;
    //显示前台首页
    @RequestMapping(value="/")
    public String showIndex()
    {
        return "index";
    }

    @RequestMapping("/search")
    public String indexSearch(Model model,String keyword,String sort, Integer pageNum, Integer pageSize, Long brandId, Float pa, Float pb) throws SolrServerException {

        PageHelper.Page<SuperPojo> page = solrService.findProductByKeyWord(keyword, sort,
                pageNum, pageSize,brandId, pa, pb);

        model.addAttribute("page", page);
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
        // 从redis中查询出品牌，并传递到页面
        List<Brand> brands = brandService.findAllFromRedis();
        model.addAttribute("brands", brands);
        model.addAttribute("brandId", brandId);

        // 回传用户选择的价格
        model.addAttribute("pa", pa);
        model.addAttribute("pb", pb);
        // 构建已选条件的map
        TreeMap<String, String> map = new TreeMap<String, String>();

        if (brandId != null) {
            // 根据品牌id 获得品牌名称
            for (Brand brand : brands) {
                //注意写等号会有问题
                if (brandId == brand.getId()) {
                    map.put("品牌", brand.getName());
                    break;
                }
            }
        }

        // 价格
        if (pa != null && pb != null) {
            if (pb == -1) {
                map.put("价格", pa + "以上");
            } else {
                map.put("价格", pa + "-" + pb);
            }
        }

        // 回显已选条件
        model.addAttribute("map", map);



        return "search";

    }

}
