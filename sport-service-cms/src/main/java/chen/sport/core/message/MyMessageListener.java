package chen.sport.core.message;

import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 19:18 2017/8/28
 * @Modified by:
 */
public class MyMessageListener implements MessageListener {
    //@Autowired
    @Override
    public void onMessage(Message message) {

    }
}
