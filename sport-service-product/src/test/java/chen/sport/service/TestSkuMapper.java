package chen.sport.service;

import chen.sport.core.mapper.SkuMapper;
import chen.sport.core.pojo.SuperPojo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description: SkuMapper测试类
 * @Date: Created in 21:24 2017/8/23
 * @Modified by:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})

public class TestSkuMapper {
    @Autowired
    private SkuMapper skuMapper;

    @Test
    public void test1() {
        List<SuperPojo> superPojos = skuMapper.findSKuAndColorByProductId(439L);
        System.out.println(superPojos.size());
        for (SuperPojo superPojo : superPojos) {
            System.out.println(superPojo.get("product_id"));
            System.out.println(superPojo.get("name"));
        }
    }

}
