package chen.sport.core.mapper;

import chen.sport.core.pojo.Brand;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description: 品牌管理mapper
 * @Date: Created in 19:05 2017/8/15
 * @Modified by:
 */
public interface BrandMapper {
    List<Brand> findByExample(Brand brand);

    Brand findById(Long id);

    void updateById(Brand brand);
}
