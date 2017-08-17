package chen.sport.service;

import chen.sport.core.pojo.Color;
import chen.sport.core.pojo.Product;
import chen.sport.core.tools.PageHelper;
import com.github.abel533.mapper.Mapper;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 16:52 2017/8/17
 * @Modified by:
 */
public interface ProductService {
    /**
     * 根据条件查询 带分页
     *
     * @param product  查询条件的模版对象
     * @param pageNum  当前页码
     * @param pageSize 每页显示行数
     * @return
     */

    PageHelper.Page<Product> findByExample(Product product, Integer pageNum, Integer pageSize);

    /**
     * 根据id查找具体的商品
     * @param productId
     * @return
     */
    Product findById(Long productId);

    /**
     * 查询所有可用颜色
     * @return
     */
    List<Color> findEnableColors();
}
