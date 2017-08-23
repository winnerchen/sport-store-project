package chen.sport.service;

import chen.sport.core.pojo.SuperPojo;
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
    List<SuperPojo> findProductByKeyWord(String keyword ,String sort)
            throws SolrServerException;

}
