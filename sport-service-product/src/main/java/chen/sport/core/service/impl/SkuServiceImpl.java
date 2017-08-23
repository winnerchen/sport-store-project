package chen.sport.core.service.impl;

import chen.sport.core.mapper.SkuMapper;
import chen.sport.core.pojo.Sku;
import chen.sport.core.pojo.SuperPojo;
import chen.sport.service.SkuService;
import com.github.abel533.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 20:12 2017/8/19
 * @Modified by:
 */
@Service("skuService")
public class SkuServiceImpl implements SkuService{
    @Autowired
    private SkuMapper skuMapper;
    @Override
    public List<Sku> findByPid(Long productId) {
        Example example = new Example(Sku.class);
        example.createCriteria().andEqualTo("productId", productId);
        List<Sku> skus = skuMapper.selectByExample(example);
        return skus;
    }

    @Override
    public int update(Sku sku) {
        int i = skuMapper.updateByPrimaryKeySelective(sku);

        return i;
    }

    @Override
    public List<SuperPojo> findSKuAndColorByProductId(Long productId) {
        return skuMapper.findSKuAndColorByProductId(productId);
    }
}
