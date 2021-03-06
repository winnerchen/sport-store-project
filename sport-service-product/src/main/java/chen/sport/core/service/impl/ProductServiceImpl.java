package chen.sport.core.service.impl;

import chen.sport.core.mapper.ColorMapper;
import chen.sport.core.mapper.ProductMapper;
import chen.sport.core.mapper.SkuMapper;
import chen.sport.core.pojo.Color;
import chen.sport.core.pojo.Product;
import chen.sport.core.pojo.Sku;
import chen.sport.core.pojo.SuperPojo;
import chen.sport.core.tools.PageHelper;
import chen.sport.service.ProductService;
import com.github.abel533.entity.Example;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private JmsTemplate jmsTemplate;
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
        example.setOrderByClause("createTime desc");
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

    @Override
    public void add(Product product) {
        // 设置默认值
        if (product.getIsShow() == null) {
            product.setIsShow(0);
        }
        if (product.getCreateTime() == null) {
            product.setCreateTime(new Date());
        }

        productMapper.insert(product);
        System.out.println("获得回显id" + product.getId());
        String[] colors = product.getColors().split(",");
        String[] sizes = product.getSizes().split(",");
        for (String color : colors) {
            for (String size : sizes) {
                Sku sku = new Sku();
                sku.setCreateTime(new Date());
                sku.setColorId(Long.parseLong(color));
                sku.setSize(size);
                sku.setProductId(product.getId());
                sku.setMarketPrice(1000.00f);
                sku.setPrice(800.00f);
                sku.setDeliveFee(20f);
                sku.setStock(0);
                sku.setUpperLimit(100);
                skuMapper.insert(sku);
            }
        }
    }

    @Override
    public void update(Product product, final String ids) throws IOException, SolrServerException {
        Example example = new Example(Product.class);
        ArrayList<Object> list = new ArrayList<>();
        String[] split = ids.split(",");
        for (String s : split) {
            list.add(Long.parseLong(s));
        }
        example.createCriteria().andIn("id", list);
        productMapper.updateByExampleSelective(product, example);

        // 需要保存的信息有：商品id、商品名称、图片地址、售价、品牌id、上架时间（可选）


        if (product.getIsShow() == 1) {
            jmsTemplate.send("productIds", new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(ids);
                }
            });

        }

    }

    @Override
    public SuperPojo findSuperPojoById(Long productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        Sku sku = new Sku();
        sku.setProductId(productId);

        List<SuperPojo> skus = skuMapper.findSKuAndColorByProductId(productId);

        SuperPojo superPojo = new SuperPojo();
        superPojo.setProperty("product", product);
        superPojo.setProperty("skus", skus);

        return superPojo;
    }


}
