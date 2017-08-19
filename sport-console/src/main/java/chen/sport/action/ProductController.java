package chen.sport.action;

import chen.sport.core.pojo.Product;
import chen.sport.core.tools.Constants;
import chen.sport.core.tools.FastDFSTool;
import chen.sport.core.tools.PageHelper;
import chen.sport.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: Yiheng Chen
 * @Description: 商品控制器
 * @Date: Created in 16:46 2017/8/17
 * @Modified by:
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;


    /**
     * 通用跳转页面
     * @param pageName
     * @return
     */
    @RequestMapping(value = "console/product/{pageName}.do")
    public String consoleProductShow(@PathVariable(value = "pageName") String pageName) {
        return "/product/" + pageName;
    }

    /**
     * 商品分页条件查询
     * @param model
     * @param name
     * @param brandId
     * @param isShow
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "console/product/list.do")
    public String consoleProductShowList(Model model, String name, Long brandId, Integer isShow,
                                         Integer pageNum, Integer pageSize) {
        Product product = new Product();
        product.setName(name);
        product.setBrandId(brandId);
        PageHelper.Page<Product> pageProduct = productService.findByExample(product, pageNum,
                pageSize);
        // 将查询出来的品牌集合传递给页面
        model.addAttribute("pageProduct", pageProduct);
        // 设置查询数据回显之将查询数据传回给页面
        model.addAttribute("name", name);
        model.addAttribute("isShow", isShow);
        int startPage = 0;
        int endPage = 0;
        int btnCount = 10;
        if (pageProduct.getPages() <= 10) {
            startPage = 1;
            endPage = pageProduct.getPages();
        } else {
            // 当前页靠前
            if (pageProduct.getPageNum() <= btnCount / 2 + 1) {
                startPage = 1;
                endPage = btnCount;
            }
            // 当前页靠后
            else if (pageProduct.getPages() - pageProduct.getPageNum() <= (btnCount - 1) / 2) {
                endPage = pageProduct.getPages();
                startPage = pageProduct.getPages() - btnCount + 1;
            }
            // 当前页靠中间
            else {
                // 当前为第10页： 5 6 7 8 9 10 11 12 13 14
                startPage = pageProduct.getPageNum() - btnCount / 2;
                endPage = startPage + btnCount - 1;
            }
        }

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


        // model.addAttribute("brandId", brandId);

        return "/product/list";
    }

    /**
     * 跳转到商品编辑页面
     * @param productId
     * @param model
     * @return
     */
    @RequestMapping(value = "console/product/showEdit.do")
    public String consoleProductShowEdit(Long productId, Model model) {
        System.out.println(productId);
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        return "/product/edit";
    }

    /**
     * 跳转到商品添加页面
     * @param model
     * @return
     */
    @RequestMapping(value = "console/product/toAdd.do")
    public String consoleProductToAdd(Model model) {
        model.addAttribute("colors", productService.findEnableColors());
        return "/product/add";
    }

    /**
     * 执行商品添加
     * @param model
     * @param product
     * @return
     */
    @RequestMapping(value = "console/product/doAdd.do")
    public String consoleProductDoAdd(Model model,Product product) {
        productService.add(product);
        return "redirect:/console/product/list.do";
    }





}
