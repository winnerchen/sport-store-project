package chen.sport.core.service.impl;

import chen.sport.core.mapper.TestTbMapper;
import chen.sport.core.pojo.TestTb;
import chen.sport.core.service.TestTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 16:19 2017/8/13
 * @Modified by:
 */
@Service
@Transactional
public class TestTbServiceImpl implements TestTbService {
    @Autowired
    private TestTbMapper testTbMapper;
    @Override
    public void add(TestTb testTb) {
        testTbMapper.add(testTb);
    }
}
