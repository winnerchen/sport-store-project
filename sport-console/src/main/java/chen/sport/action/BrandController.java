package chen.sport.action;

import chen.sport.core.pojo.Brand;
import chen.sport.core.tools.PageHelper;
import chen.sport.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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


    // 品牌
    @RequestMapping(value = "console/brand/{pageName}.do")
    public String consoleBrandShow(
            @PathVariable(value = "pageName") String pageName) {
        return "/brand/" + pageName;
    }


    /**
     * 品牌列表查询
     *
     * @param model
     * @param name
     * @param isDisplay
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "console/brand/list.do")
    public String consoleBrandList(Model model, String name, Integer isDisplay, Integer pageNum, Integer pageSize) {

        System.out.println(name);
        System.out.println(isDisplay);
        Brand brand = new Brand();
        brand.setName(name);
        brand.setIsDisplay(isDisplay);

        // 开始分页查询
        PageHelper.Page<Brand> pageBrand = brandService.findByExample(brand, pageNum, pageSize);

        System.out.println(pageBrand.getResult().size());

        model.addAttribute("pageBrand", pageBrand);
        // 设置查询数据回显之将查询数据传回给页面
        model.addAttribute("name", name);
        model.addAttribute("isDisplay", isDisplay);

        return "/brand/list";

    }


    /**
     * 根据id查找品牌并回显到编辑页面
     *
     * @param brandId
     * @param model
     * @return
     */
    @RequestMapping(value = "console/brand/toEdit.do")
    public String consoleBrandToEdit(Long brandId, Model model) {
        System.out.println(brandId);
        Brand brand = brandService.findById(brandId);
        model.addAttribute("brand", brand);
        return "/brand/edit";
    }
    @RequestMapping(value = "console/brand/doEdit.do")
    public String consoleBrandDoEdit(Brand brand) {
        System.out.println("do edit: id is " + brand.getId());

        brandService.updateById(brand);

        return "redirect:/console/brand/list.do";
    }



}
