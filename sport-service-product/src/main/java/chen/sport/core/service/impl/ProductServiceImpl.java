package chen.sport.core.service.impl;

import chen.sport.core.mapper.ColorMapper;
import chen.sport.core.mapper.ProductMapper;
import chen.sport.core.pojo.Color;
import chen.sport.core.pojo.Product;
import chen.sport.core.tools.PageHelper;
import chen.sport.service.ProductService;
import com.github.abel533.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description: 商品服务实现类
 * @Date: Created in 17:03 2017/8/17
 * @Modified by:
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ColorMapper colorMapper;
    @Override
    public PageHelper.Page<Product> findByExample(Product product, Integer pageNum, Integer
            pageSize) {
        // TODO Auto-generated method stub
        if(product.getName()==null)
        {
            product.setName("");
        }
        // 开始分页
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(Product.class);
        example.createCriteria().andLike("name", "%" + product.getName() + "%");
        List<Product> products = productMapper.selectByExample(example);

        // 结束分页
        PageHelper.Page<Product> endPage = PageHelper.endPage();
        return endPage;

    }

    @Override
    public Product findById(Long productId) {
        Product product = new Product();
        product.setId(productId);
        return productMapper.selectByPrimaryKey(productId);
    }

    @Override
    public List<Color> findEnableColors() {
        Example example = new Example(Color.class);
        example.createCriteria().andNotEqualTo("parentId", 0 + "");
        List<Color> colors = colorMapper.selectByExample(example);
        return colors;
    }
}
