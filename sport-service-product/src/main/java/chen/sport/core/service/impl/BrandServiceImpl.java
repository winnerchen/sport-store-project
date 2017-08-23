package chen.sport.core.service.impl;

import chen.sport.core.mapper.BrandMapper;
import chen.sport.core.pojo.Brand;
import chen.sport.core.tools.PageHelper;
import chen.sport.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 19:17 2017/8/15
 * @Modified by:
 */
@Service("brandService")
@Transactional
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private Jedis jedis;

    @Override
    public PageHelper.Page findByExample(Brand brand, Integer pageNum,
                                         Integer pageSize
    ) {
        PageHelper.startPage(pageNum, pageSize);
        List<Brand> brands = brandMapper.findByExample(brand);
        System.out.println(brands);
        PageHelper.Page endPage = PageHelper.endPage();
        return endPage;
    }

    @Override
    public Brand findById(Long id) {
        return brandMapper.findById(id);
    }

    @Override
    public void updateById(Brand brand) {
        jedis.hset("brand", String.valueOf(brand.getId()), brand.getName());
        brandMapper.updateById(brand);
    }
    @Override
    public List<Brand> findAllFromRedis() {
        Map<String, String> hgetAll = jedis.hgetAll("brand");
        // 将查询的结果放入到品牌对象集合中
        List<Brand> brands = new ArrayList<Brand>();

        Set<Map.Entry<String, String>> entrySet = hgetAll.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            Brand brand = new Brand();
            brand.setId(Long.parseLong(entry.getKey()));
            brand.setName(entry.getValue());
            brands.add(brand);
        }
        return brands;
    }

}
