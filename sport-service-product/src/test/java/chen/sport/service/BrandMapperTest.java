package chen.sport.service;

import chen.sport.core.mapper.BrandMapper;
import chen.sport.core.pojo.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 20:11 2017/8/15
 * @Modified by:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BrandMapperTest {
    @Autowired
    private BrandMapper brandMapper;

    @Test
    public void testFindAll() {
        List<Brand> brands = brandMapper.findByExample(null);
        System.out.println(brands.size());
    }
}
