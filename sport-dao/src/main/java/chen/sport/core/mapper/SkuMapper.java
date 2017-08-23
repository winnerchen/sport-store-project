package chen.sport.core.mapper;

import chen.sport.core.pojo.Sku;
import chen.sport.core.pojo.SuperPojo;
import com.github.abel533.mapper.Mapper;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 16:35 2017/8/19
 * @Modified by:
 */
public interface SkuMapper extends Mapper<Sku> {
    /**
     * 根据商品id查询某商品的库存，并且将颜色名称，通过对颜色表连接查询的方式也带出来
     *
     * @param productId
     * @return
     */
    public List<SuperPojo> findSKuAndColorByProductId(Long productId);

}
