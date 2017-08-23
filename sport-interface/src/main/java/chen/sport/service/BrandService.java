package chen.sport.service;

import chen.sport.core.pojo.Brand;
import chen.sport.core.tools.PageHelper;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description: 品牌服务类接口
 * @Date: Created in 19:13 2017/8/15
 * @Modified by:
 */
public interface BrandService {
    /**
     * @description 根据案例查找品牌
     * @param brand
     * @return 品牌结果集合
     */
     PageHelper.Page findByExample(Brand brand, Integer pageNum, Integer pageSize);

    Brand findById(Long id);

    void updateById(Brand brand);

     List<Brand> findAllFromRedis();
}
