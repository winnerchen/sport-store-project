package chen.sport.core.service.impl;

import chen.sport.core.mapper.BrandMapper;
import chen.sport.core.pojo.Brand;
import chen.sport.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Override
    public List<Brand> findByExample(Brand brand) {
        return brandMapper.findByExample(brand);
    }
}
