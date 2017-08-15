package chen.sport.action;

import chen.sport.core.pojo.Brand;
import chen.sport.core.tools.Encoding;
import chen.sport.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description: 品牌管理控制器
 * @Date: Created in 19:20 2017/8/15
 * @Modified by:
 */
@Controller
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 品牌查询,包含条件和分页
     * @param pageName
     * @param model
     * @param name
     * @param isDisplay
     * @return
     */
    // 品牌
    @RequestMapping(value = "console/brand/{pageName}.do")
    public String consoleBrandShow(@PathVariable(value = "pageName") String pageName, Model
            model, String name, Integer isDisplay) {

        // 品牌查询
        if (pageName.equals("list")) {
            System.out.println(name);
            System.out.println(isDisplay);
            Brand brand = new Brand();
            brand.setName(name);
            brand.setIsDisplay(isDisplay);
            List<Brand> brands = brandService.findByExample(brand);
            System.out.println("品牌的数量:" + brands.size());
            model.addAttribute("brandList", brands);
            // 设置查询数据回显之将查询数据传回给页面
            model.addAttribute("name", name);
            model.addAttribute("isDisplay", isDisplay);

        }
        return "/brand/" + pageName;
    }

}
