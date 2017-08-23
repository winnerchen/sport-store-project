package chen.sport.service;

import chen.sport.core.pojo.SuperPojo;
import chen.sport.core.tools.PageHelper;
import org.apache.solr.client.solrj.SolrServerException;

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

}
