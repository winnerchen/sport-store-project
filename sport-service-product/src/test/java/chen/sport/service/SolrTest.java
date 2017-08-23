package chen.sport.service;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 15:15 2017/8/21
 * @Modified by:
 */
public class SolrTest {
    /**
     * 创建索引（传统方式）
     *
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void createIndex1() throws SolrServerException, IOException {
        // 使用HttpSolr服务端（HttpSolrServer） 创建solr服务器端对象
        HttpSolrServer solrServer = new HttpSolrServer(
                "http://192.168.56.201:8080/solr/collection1");
        // 使用solr输入文档（SolrInputDocument） 创建文档对象
        SolrInputDocument document = new SolrInputDocument();
        // 添加字段到文档对象
        document.addField("id", "1");
        document.addField("name_ik", "白富美范冰冰");
        //添加文档到solr服务器对象
        solrServer.add(document);
        // 提交
        solrServer.commit();
    }

    @Test
    public void testStringUtils() {
        String s = "mystr";
        String[] split = s.split(",");
        for (String s1 : split) {
            System.out.println(s1);
        }
    }

}
