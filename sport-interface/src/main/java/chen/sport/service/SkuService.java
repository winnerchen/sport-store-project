package chen.sport.service;

import chen.sport.core.pojo.Sku;

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
}
