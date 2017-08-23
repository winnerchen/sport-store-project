package chen.sport.action;

import chen.sport.core.pojo.Brand;
import chen.sport.core.pojo.Product;
import chen.sport.core.pojo.SuperPojo;
import chen.sport.core.tools.PageHelper;
import chen.sport.service.BrandService;
import chen.sport.service.ProductService;
import chen.sport.service.SolrService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * @Author: Yiheng Chen
 * @Description: 商品展示控制器
 * @Date: Created in 19:13 2017/8/20
 * @Modified by:
 */
@Controller
public class ProductController {
    @Autowired
    private SolrService solrService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductService productService;
    @RequestMapping("/product/detail")
    public String showSingleProduct(Model model, Long productId) {
        System.out.println("商品id:" + productId);
        SuperPojo superPojo = productService.findSuperPojoById(productId);
        Product product = (Product) superPojo.get("product");
        List skus = (List) superPojo.get("skus");

        System.out.println("商品名称：" + product.getName());
        System.out.println("sku数量：" + skus.size());

        HashMap<Long, String> colors = new HashMap<>();

        for (Object object : skus) {
            SuperPojo sku = (SuperPojo) object;
            // 将颜色添加到hm集合中，利用hm集合来去除重复   key是颜色的id  value是颜色名称
            colors.put((Long) sku.get("color_id"), (String) sku.get("name"));
        }
        superPojo.setProperty("colors", colors);
        // 将商品对象和该商品的库存对象的集合(万能pojo对象)传递给页面
        model.addAttribute("superPojo", superPojo);

        return "product";
    }
}
