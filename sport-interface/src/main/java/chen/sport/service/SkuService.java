package chen.sport.service;

import chen.sport.core.pojo.Sku;
import chen.sport.core.pojo.SuperPojo;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description: Sku业务接口
 * @Date: Created in 20:10 2017/8/19
 * @Modified by:
 */
public interface SkuService {
    public List<Sku> findByPid(Long productId);

    int update(Sku sku);

    /**
     * 根据商品id查询某商品的库存，并且将颜色名称，通过对颜色表连接查询的方式也带出来
     *
     * @param productId
     * @return
     */
    public List<SuperPojo> findSKuAndColorByProductId(Long productId);

}
