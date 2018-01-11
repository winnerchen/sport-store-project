package chen.sport.core.service.impl;

import chen.sport.core.mapper.ProductMapper;
import chen.sport.core.mapper.SkuMapper;
import chen.sport.core.pojo.Product;
import chen.sport.core.pojo.Sku;
import chen.sport.core.pojo.SuperPojo;
import chen.sport.core.tools.PageHelper;
import chen.sport.service.SolrService;
import com.github.abel533.entity.Example;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 15:27 2017/8/21
 * @Modified by:
 */
@Service("solrService")
public class SolrServiceImpl implements SolrService{
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Override
    public PageHelper.Page<SuperPojo> findProductByKeyWord(String keyword, String sort, Integer pageNum, Integer pageSize,Long brandId, Float pa, Float pb)
            throws SolrServerException {

        // 设置查询条件
        SolrQuery solrQuery = new SolrQuery("name_ik:" + keyword);

        //设置过滤条件
        if (brandId != null) {
            solrQuery.addFilterQuery("brandId:" + brandId);
        }
        // 价格
        if (pa != null && pb != null) {
            if (pb == -1) {
                solrQuery.addFilterQuery("price:[" + pa + " TO *]");
            } else {
                solrQuery.addFilterQuery("price:[" + pa + " TO " + pb + "]");
            }
        }


        PageHelper.Page<SuperPojo> page = new PageHelper.Page<>(pageNum, pageSize);

        solrQuery.setStart(page.getStartRow());
        solrQuery.setRows(page.getPageSize());

        // 设置排序
        // solrQuery.setSort("price", ORDER.asc);
        if (sort != null && sort.trim().length() > 0) {
            solrQuery.setSort(
                    new SolrQuery.SortClause(sort.split(" ")[0], sort.split(" ")[1]));
        }

        // 设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("name_ik");
        solrQuery.setHighlightSimplePre("<span style='color:red'>");
        solrQuery.setHighlightSimplePost("</span>");


        // 开始查询
        QueryResponse response = solrServer.query(solrQuery);

        // 获得高亮数据集合
        Map<String, Map<String, List<String>>> highlighting = response
                .getHighlighting();

        // 获得结果集
        SolrDocumentList results = response.getResults();

        // 获得总数量
        long numFound = results.getNumFound();

        page.setTotal(numFound);

        // 将结果集中的信息封装到商品对象中
        // 注意：由于原商品对象中并没有价格属性，而价格属性本应该是在商品对象的子对象库存对象中，
        // 而本次设计并不打算使用类似于hibernate的在pojo中做对象的相应关联，所以这里，我们可以使用万能对象来装载数据
        // 一个万能对象就可以等同于从数据库查询（包括连接查询）出的结果表中的一条数据

        // 创建商品对象（万能对象）集合
        List<SuperPojo> superProducts = new ArrayList<SuperPojo>();

        for (SolrDocument solrDocument : results) {
            // 创建商品对象
            SuperPojo superProduct = new SuperPojo();
            // 商品id
            String id = (String) solrDocument.get("id");
            superProduct.setProperty("id", id);

            // 商品名称

            Map<String, List<String>> map = highlighting.get(id);

            String name = map.get("name_ik").get(0);
            //String name = (String) solrDocument.get("name_ik");
            superProduct.setProperty("name", name);

            // 图片地址
            String imgUrl = (String) solrDocument.get("url");
            superProduct.setProperty("imgUrl", imgUrl);

            // 商品最低价格
            Float price = (Float) solrDocument.get("price");
            superProduct.setProperty("price", price);

            // 品牌id
            String brandId2 = (String) solrDocument.get("brandId");
            superProduct.setProperty("brandId", brandId2);

            // 将万能商品对象添加到集合中
            superProducts.add(superProduct);
        }
        page.setResult(superProducts);
        return page;
    }

    public void addProduct(String ids) throws IOException, SolrServerException {
        Example example = new Example(Product.class);

        // 将ids的字符串转成list集合
        List arrayList = new ArrayList<Object>();
        String[] split = ids.split(",");
        for (String string : split) {
            arrayList.add(string);
        }
        // 设置批量修改的id条件
        example.createCriteria().andIn("id", arrayList);
        // 查询ids中的所有商品
        List<Product> products = productMapper.selectByExample(example);
        for (Product product1 : products) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", product1.getId());
            doc.addField("name_ik", product1.getName());
            doc.addField("url", product1.getImgUrl().split(",")[0]);
            doc.addField("brandId", product1.getBrandId());

            Example example1 = new Example(Sku.class);

            example1.createCriteria().andEqualTo("productId", product1.getId());
            example1.setOrderByClause("price asc");
            PageHelper.startPage(1,1);
            List<Sku> skuList = skuMapper.selectByExample(example1);
            PageHelper.endPage();
            doc.addField("price", skuList.get(0).getPrice());
            solrServer.add(doc);
            solrServer.commit();

        }
    }

}
