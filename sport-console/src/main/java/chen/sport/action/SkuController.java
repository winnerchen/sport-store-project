package chen.sport.action;

import chen.sport.core.pojo.Sku;
import chen.sport.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description: Sku控制器
 * @Date: Created in 20:03 2017/8/19
 * @Modified by:
 */
@Controller
public class SkuController {
    @Autowired
    private SkuService skuService;

    /**
     * 根据商品id查找sku列表
     *
     * @param model
     * @param productId
     * @return
     */
    @RequestMapping("console/sku/list")
    public String consoleSkuShowList(Model model, Long productId) {
        System.out.println(productId);

        List<Sku> skus = skuService.findByPid(productId);
        System.out.println("库存数量：" + skus.size());
        model.addAttribute("skus", skus);
        return "sku/list";
    }

    /**
     * 修改库存数据
     * @param sku
     * @return
     */
    @RequestMapping("console/sku/update")
    @ResponseBody
    public String update(Sku sku) {
        System.out.println(sku);
        int update=skuService.update(sku);

        return update+"";
    }
}
