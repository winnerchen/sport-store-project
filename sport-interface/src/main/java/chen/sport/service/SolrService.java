package chen.sport.service;

import chen.sport.core.pojo.Product;
import chen.sport.core.pojo.SuperPojo;
import chen.sport.core.tools.PageHelper;
import com.github.abel533.entity.Example;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 15:27 2017/8/21
 * @Modified by:
 */
public interface SolrService {

    /**
     * 根据关键字搜索商品
     * @param keyword
     * @return
     * @throws SolrServerException
     */
    PageHelper.Page<SuperPojo> findProductByKeyWord(String keyword , String sort, Integer pageNum, Integer pageSize,Long brandId, Float pa, Float pb)
            throws SolrServerException;

    void addProduct(String ids) throws IOException, SolrServerException;

}
