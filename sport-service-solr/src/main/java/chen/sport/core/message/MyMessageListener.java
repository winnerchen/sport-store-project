package chen.sport.core.message;

import chen.sport.service.SolrService;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 17:10 2017/8/24
 * @Modified by:
 */
public class MyMessageListener implements MessageListener{
    @Autowired
    private SolrService solrService;
    @Override
    public void onMessage(Message message) {
        ActiveMQTextMessage amessage = (ActiveMQTextMessage) message;
        try {
            String ids = amessage.getText();
            System.out.println("消费方接收到的消息：" + ids);
            // 添加商品信息到solr服务器
            solrService.addProduct(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
